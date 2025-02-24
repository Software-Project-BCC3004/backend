# Design Pattern: Singleton

## Definição
O **Singleton** é um padrão de projeto criacional que garante que uma classe tenha **apenas uma instância** ao longo do ciclo de vida da aplicação, enquanto fornece um ponto de acesso global para essa instância. No contexto do **Spring Boot**, os componentes anotados com `@Repository`, `@Service` e `@RestController` são gerenciados automaticamente pelo Spring como **Singletons**, significando que só haverá uma única instância de cada bean durante a execução da aplicação.

## Implementação no Projeto
Abaixo está um exemplo de como o padrão Singleton é aplicado no seu projeto utilizando Spring Boot:

### Repositório
O `PacienteRepository` é um repositório do Spring Data JPA, sendo gerenciado pelo container do Spring como um Singleton.

```java
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByNomePaciente(String nomePaciente);
}
```

### Serviço
O `PacienteServices` é um service anotado com `@Service`, garantindo que o Spring crie e gerencie apenas **uma instância** dele.

```java
@Service
public class PacienteServices {
    @Autowired
    private PacienteRepository pacienteRepository;

    public ResponseEntity<Paciente> getPacienteByNome(String nomePaciente) {
        Optional<Paciente> paciente = pacienteRepository.findByNomePaciente(nomePaciente);
        return paciente.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
```

### Controller
O `PacienteController` também é um Singleton gerenciado pelo Spring, garantindo que apenas uma instância exista na aplicação.

```java
@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteServices pacienteServices;

    @GetMapping("/consultar/nome/{nome}")
    public ResponseEntity<Paciente> getPacienteByNome(@PathVariable String nome) {
        return pacienteServices.getPacienteByNome(nome);
    }
}
```

## Benefícios do Singleton no Spring Boot
- **Eficiência de Memória:** Evita a criação de múltiplas instâncias desnecessárias.
- **Facilidade de Gerenciamento:** O Spring gerencia automaticamente o ciclo de vida dos beans Singleton.
- **Melhor Performance:** Reduz o overhead de criação e destruição de objetos repetitivos.

## Referências
- [Refactoring Guru - Singleton](https://refactoring.guru/pt-br/design-patterns/singleton)
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes)

