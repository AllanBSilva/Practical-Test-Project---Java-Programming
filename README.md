# Projeto de Teste Prático - Programação Java

## Descrição
Este é um projeto Java desenvolvido para um teste prático de programação, que simula o gerenciamento de uma lista de funcionários em uma indústria. O sistema executa uma série de ações em cima dessa lista, incluindo manipulação de dados, cálculos e agrupamento de informações, além de realizar formatação de valores e validações.

## Requisitos
O programa é estruturado em várias classes que realizam as seguintes ações:


### 1. **Classe Pessoa**

A classe `Pessoa` representa uma pessoa genérica e é a classe base para a classe `Funcionário`. Ela contém as informações básicas sobre o nome e a data de nascimento de uma pessoa.

#### Atributos:
- **`nome`** (String): O nome completo da pessoa.
- **`dataNascimento`** (LocalDate): A data de nascimento da pessoa, representada por um objeto `LocalDate`.

#### Métodos:
- **`getName()`**: Retorna o nome da pessoa.
- **`setName(String name)`**: Define o nome da pessoa.
- **`getBirthDate()`**: Retorna a data de nascimento da pessoa.
- **`setBirthDate(LocalDate birthDate)`**: Define a data de nascimento da pessoa.
  
- **`getAge()`**: Calcula e retorna a idade da pessoa com base na diferença entre o ano atual e o ano de nascimento. Para maior precisão, a idade leva em consideração o mês e o dia da data de nascimento, mas a implementação atual calcula a idade apenas pelo ano.
  
- **`getFormattedBirthDate()`**: Retorna a data de nascimento da pessoa formatada no padrão `dd/mm/yyyy`.
  - Exemplo: Se a data de nascimento for `1990-05-12`, a saída será `12/5/1990`.

#### Exemplo de Uso:

```java
Person person = new Person("Maria", LocalDate.of(1990, 5, 12));

// Exibindo o nome da pessoa
System.out.println(person.getName()); // Saída: Maria

// Exibindo a data de nascimento formatada
System.out.println(person.getFormattedBirthDate()); // Saída: 12/5/1990

// Calculando a idade da pessoa
System.out.println(person.getAge()); // Saída: 35 (se a data atual for 2025)
```



### 2. Classe **Funcionário** (extende `Pessoa`)

A classe `Funcionário` herda da classe `Pessoa` e representa um funcionário na empresa. Ela possui os seguintes atributos e métodos:

#### Atributos:
- **`salario`** (BigDecimal): Representa o salário do funcionário. O salário é validado para garantir que não seja negativo e que não seja inferior ao salário mínimo de R$1212,00.
- **`funcao`** (String): Representa a função ou cargo do funcionário na empresa.

#### Métodos:
- **`increaseSalary(double percentage)`**: Recebe um valor percentual e aumenta o salário do funcionário de acordo com esse percentual.
  - Exemplo: Se o salário é R$2000,00 e o percentual fornecido é 10%, o novo salário será R$2200,00.
  
- **`getFormattedSalary()`**: Retorna o salário do funcionário como uma string formatada. O formato inclui separadores de milhar (ponto) e vírgula como separador decimal.
  - Exemplo: R$ 2.000,50 será exibido como "2.000,50".
  
- **`setSalary(BigDecimal salary)`**: Define o salário do funcionário. Caso o salário seja negativo ou inferior ao salário mínimo, será lançada uma exceção `IllegalArgumentException`.
  
- **`setRole(String role)`**: Define a função ou cargo do funcionário. Caso o cargo seja `null` ou uma string vazia, uma exceção `IllegalArgumentException` será lançada.

- **`isAdult()`**: Verifica se o funcionário é maior de idade (18 anos ou mais). Retorna `true` se o funcionário for adulto e `false` caso contrário.

- **`isBirthDateValid()`**: Verifica se a data de nascimento do funcionário é válida (se é uma data anterior ao dia de execução do código). Retorna `true` se a data de nascimento for válida e `false` caso contrário.

#### Exemplo de Uso:

```java
Employee employee = new Employee("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador");

// Aumento de 10% no salário
employee.increaseSalary(10);

// Exibindo salário formatado
System.out.println(employee.getFormattedSalary()); // Saída: 2.284,38

// Verificando se o funcionário é maior de idade
System.out.println(employee.isAdult()); // Saída: true ou false

// Validando a data de nascimento
System.out.println(employee.isBirthDateValid()); // Saída: true ou false
```

### 3. **Classe Principal (`App.java`)**

A classe `App` é responsável por orquestrar as principais ações relacionadas aos funcionários. Nela, são executadas várias operações, como a inserção de dados, manipulação das informações e apresentação dos resultados.

#### Ações realizadas:

- **Inserir Funcionários**: Inicializa a lista de funcionários com dados fictícios, como nome, data de nascimento, salário e função. Os dados são definidos manualmente na classe `App`, utilizando o construtor da classe `Employee`.

- **Remover João**: O funcionário com o nome "João" é removido da lista utilizando o método `removeIf()`, baseado no nome do funcionário.

- **Imprimir Funcionários**: Exibe as informações de todos os funcionários, incluindo:
  - Nome
  - Data de nascimento (formatada no padrão `dd/mm/yyyy`)
  - Salário (formatado com separador de milhar e vírgula como decimal)
  - Função

- **Aumento de 10% no Salário**: Todos os funcionários recebem um aumento de 10% no salário, através do método `increaseSalary(10)` da classe `Employee`.

- **Agrupar por Função**: Agrupa os funcionários por suas funções em um `Map`, onde a chave é a função e o valor é uma lista de funcionários com essa função.

- **Filtrar Aniversariantes de Outubro e Dezembro**: Exibe os funcionários que fazem aniversário nos meses de outubro e dezembro, utilizando a função `filter()` para filtrar por mês.

- **Imprimir Funcionário Mais Velho**: Identifica e exibe o funcionário mais velho, comparando a idade de todos os funcionários com o método `max()`. O nome e a idade do funcionário mais velho são impressos.

- **Ordenação Alfabética**: Exibe todos os funcionários em ordem alfabética com base no nome, utilizando o método `sorted()`.

- **Total Salarial**: Calcula o total de salários de todos os funcionários e exibe o valor total utilizando o método `reduce()`.

- **Salários Mínimos**: Calcula e exibe quantos salários mínimos cada funcionário recebe, considerando que o salário mínimo é de R$1212,00. Esse cálculo é feito dividindo o salário de cada funcionário pelo salário mínimo.

#### Exemplo de uso e saída:

```java
public class App {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // Adicionando funcionários
        employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        // ... (outros funcionários)

        // Remover João
        employees.removeIf(employee -> employee.getName().equals("João"));

        // Imprimir funcionários
        employees.forEach(employee -> {
            System.out.println("Nome: " + employee.getName());
            System.out.println("Data de Nascimento: " + employee.getFormattedBirthDate());
            System.out.println("Salário: R$" + employee.getFormattedSalary());
            System.out.println("Função: " + employee.getRole());
            System.out.println("-----------------------------------------------");
        });

        // Aumento de salário de 10%
        employees.forEach(employee -> employee.increaseSalary(10));

        // Agrupar funcionários por função
        Map<String, List<Employee>> employeesByRole = employees.stream()
            .collect(Collectors.groupingBy(Employee::getRole));

        // Imprimir funcionários por função
        employeesByRole.forEach((role, empList) -> {
            System.out.println(role + ":");
            empList.forEach(employee -> System.out.println("  - " + employee.getName()));
        });

        // Filtrar aniversariantes de outubro e dezembro
        employees.stream()
            .filter(employee -> employee.getBirthDate().getMonthValue() == 10 || employee.getBirthDate().getMonthValue() == 12)
            .forEach(employee -> System.out.println(employee.getName()));

        // Imprimir o funcionário mais velho
        Employee oldest = employees.stream()
            .max(Comparator.comparingInt(Employee::getAge))
            .orElseThrow(NoSuchElementException::new);
        System.out.println("O funcionário mais velho é " + oldest.getName() + " com " + oldest.getAge() + " anos.");

        // Ordenar funcionários por nome
        employees.stream()
            .sorted(Comparator.comparing(Employee::getName))
            .forEach(employee -> System.out.println(employee.getName()));

        // Calcular o total de salários
        BigDecimal totalSalary = employees.stream()
            .map(Employee::getSalary)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários de todos os funcionários: R$" + totalSalary);

        // Calcular salários mínimos
        final BigDecimal minimumWage = BigDecimal.valueOf(1212.00);
        employees.forEach(employee -> {
            BigDecimal salaryInMinWages = employee.getSalary().divide(minimumWage, 2, RoundingMode.HALF_UP);
            System.out.println(employee.getName() + " ganha " + salaryInMinWages + " salários mínimos.");
        });
    }
}
```

## Estrutura do Projeto

O projeto é estruturado da seguinte maneira:

- **Classe `Pessoa`**: Representa uma pessoa, com atributos de nome e data de nascimento.
- **Classe `Funcionário`**: Extende `Pessoa` e adiciona os atributos de salário e função. Inclui métodos para calcular aumentos salariais e formatar salários.
- **Classe `App`**: Contém o método principal onde todas as operações descritas são executadas, como inserção de dados, impressão das informações e manipulação dos dados dos funcionários.
- **Classes `FileOutputTXT` e `FileOutputExcel`**: Responsáveis por salvar os dados dos funcionários em arquivos de texto e Excel, respectivamente.
- **Classe `DerbyDatabase`**: Realiza a interação com um banco de dados Derby, criando tabelas e inserindo dados dos funcionários.


## Como Rodar o Projeto

### Requisitos

Certifique-se de ter o **Java 17** ou superior instalado na sua máquina.

1. Clone este repositório:
    ```bash
    git https://github.com/AllanBSilva/Practical-Test-Project---Java-Programming.git
    cd Practical-Test-Project---Java-Programming
    ```

2. Compile e rode o projeto:
    ```bash
    javac -d bin src/com/example/*.java
    java -cp bin com.example.App
    ```

### Resultado Esperado

Ao executar o programa, a classe `App.java` realizará as ações conforme descritas, imprimindo os resultados diretamente no terminal. O programa exibe mensagens informativas que indicam o sucesso ou a falha de cada operação. As informações dos funcionários serão apresentadas com a formatação adequada, como nomes, datas de nascimento, salários e funções.

O fluxo de execução incluirá:

- Inserção de dados dos funcionários.
- Remoção do funcionário "João".
- Exibição das informações de todos os funcionários, com formatação correta de salário e data de nascimento.
- Aumento de 10% no salário de todos os funcionários.
- Agrupamento dos funcionários por função.
- Filtragem e exibição dos aniversariantes de outubro e dezembro.
- Exibição do funcionário mais velho, juntamente com seu nome e idade.
- Ordenação alfabética dos funcionários.
- Cálculo do total salarial de todos os funcionários.
- Cálculo do número de salários mínimos que cada funcionário recebe, com base no valor de R$1212,00.

Além disso, o programa inclui **operações extras**, como exportação dos dados dos funcionários para arquivos (TXT e Excel) e inserção no banco de dados Derby, com mensagens indicando o status de cada operação.

---

## Testes

A aplicação inclui **testes unitários** utilizando o JUnit 5. Os testes cobrem várias funcionalidades cruciais da aplicação para garantir que o comportamento do sistema esteja conforme esperado. As principais funcionalidades testadas são:

- **Validação de salário não negativo**: Verifica se o sistema impede a atribuição de um salário negativo.
- **Validação de salário mínimo**: Testa se o sistema não permite a definição de um salário abaixo do salário mínimo.
- **Cálculo de total de salários**: Confirma que o cálculo do total salarial de todos os funcionários está correto.
- **Aumento de salário**: Testa se o aumento de 10% no salário dos funcionários é aplicado corretamente.
- **Formato de exibição de salários**: Verifica se os salários são formatados corretamente com separadores de milhar e vírgula para o decimal.
- **Cálculo da idade do funcionário**: Confirma que a idade dos funcionários está sendo calculada corretamente com base na data de nascimento.
- **Funcionários adicionados e removidos**: Verifica se os funcionários podem ser corretamente adicionados à lista e removidos quando necessário.
- **Verificação dos campos do funcionário**: Testa a integridade dos dados do funcionário, como nome, cargo e data de nascimento.
- **Construtor do funcionário**: Verifica se o construtor da classe `Employee` está funcionando corretamente e atribui os valores esperados.

### Como rodar os testes

1. Se você estiver utilizando o **Maven**, execute:
    ```bash
    mvn test
    ```

2. Ou, caso esteja usando **JUnit diretamente**, compile e rode os testes:
    ```bash
    javac -d bin src/com/example/*.java src/com/example/test/*.java
    java -cp bin org.junit.runner.JUnitCore com.example.AppTest
    ```

### Exemplos de Testes

Aqui estão alguns dos testes realizados no projeto:

- **Testar Adição de Funcionário**: 
    - **Objetivo**: Adiciona um novo funcionário e verifica se ele foi corretamente inserido na lista.
    - **Testado**: A lista de funcionários aumenta e o novo funcionário está presente nela.
  
- **Testar Remoção de Funcionário**:
    - **Objetivo**: Remove um funcionário com nome específico (João) da lista e verifica se ele foi removido.
    - **Testado**: A lista é corretamente atualizada e o funcionário não está mais presente.
  
- **Testar Cálculo do Total Salarial**:
    - **Objetivo**: Calcula o total de salários dos funcionários e verifica se o valor somado está correto.
    - **Testado**: O total salarial é calculado corretamente com base nos salários dos funcionários.

- **Testar Aumento de Salário**:
    - **Objetivo**: Aplica um aumento de 10% no salário de todos os funcionários e verifica se o aumento foi aplicado corretamente.
    - **Testado**: O salário de todos os funcionários é aumentado em 10% sem que haja valores negativos ou inconsistentes.

- **Testar Formatação de Salário**:
    - **Objetivo**: Verifica se os salários são exibidos com separadores de milhar e vírgula como decimal.
    - **Testado**: A formatação do salário é verificada para garantir que ele segue o padrão correto de exibição.

- **Testar Validação de Salário Negativo**:
    - **Objetivo**: Verifica se um erro é gerado quando se tenta definir um salário negativo.
    - **Testado**: Um `IllegalArgumentException` é lançado quando o salário é inferior a zero.

Esses testes garantem que a aplicação funcione corretamente e que os dados dos funcionários sejam manipulados de forma consistente e segura.


## Considerações Finais

Este projeto demonstra como modelar uma aplicação simples de gerenciamento de funcionários utilizando conceitos de orientação a objetos. Ele também faz uso de banco de dados, manipulação de arquivos e testes unitários para garantir que as funcionalidades estejam corretas.


