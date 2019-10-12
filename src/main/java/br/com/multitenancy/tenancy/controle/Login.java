package br.com.multitenancy.tenancy.controle;

import br.com.multitenancy.tenancy.config.tenancy_dois.TenancyAbstractImpl;
import br.com.multitenancy.tenancy.config.tenancy_dois.TenantContext;
import br.com.multitenancy.tenancy.modelo.tenancy.User;
import br.com.multitenancy.tenancy.repositorio.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login")
@CrossOrigin
public class Login {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TenancyAbstractImpl tenancyAbstractImpl;

    @PostMapping
    public ResponseEntity<?> logar(@RequestBody User user) {
        List<User> findAll = userRepository.findAll();
        Stream<User> filter = findAll.stream().filter(usuario -> {
            return user.getTenancyId().equals(usuario.getTenancyId()) && user.getNome().equals(usuario.getNome()) && user.getSenha().equals(usuario.getSenha());
        });
        Optional<User> findAny = filter.findAny();
        if (findAny.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        User findUser = findAny.get();
        inicializarNovaConexao(user);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    private void inicializarNovaConexao(User user) {
        Object datasource = TenantContext.getResolvers().get(user.getTenancyId());
        if (datasource == null) {
            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/" + user.getBanco());
            driverManagerDataSource.setUsername("root");
            driverManagerDataSource.setPassword("123456");
            TenantContext.getResolvers().put(user.getTenancyId(), driverManagerDataSource);
            tenancyAbstractImpl.setTargetDataSources(TenantContext.getResolvers());
            tenancyAbstractImpl.afterPropertiesSet();
        }
        TenantContext.TENANT_ID = user.getTenancyId();
    }

}
