# Curso Selenium WebDriver em Java T1606355

## Objetivo
Este está sendo desenvolvido no treinamento pratico de automacao de testes web com Selenium WebDriver em Java.

O foco e aprender e praticar:
- identificacao de elementos na pagina
- interacao com campos e componentes web
- validacao de resultados com assercoes
- estrutura basica de um teste automatizado com setup e teardown

## Stack do projeto
- Java
- Selenium WebDriver (`selenium-java` 4.43.0)
- JUnit 5 (`junit-jupiter-api` 5.14.4)
- WebDriverManager (`webdrivermanager` 6.3.4)
- Maven (gerenciamento de dependencias e execucao dos testes)
- Navegador utilizado no exemplo: Mozilla Firefox (`FirefoxDriver`)

## Estrutura principal
- `src/test/java/com/test/WebElementsTest.java`: classe de teste com exemplo de interacao e validacao de campo de texto
- `pom.xml`: configuracao do Maven e dependencias do projeto

## Como executar
### Pre-requisitos
- Java instalado
- Maven instalado
- Firefox instalado

### Executar os testes
Na raiz do projeto, rode:

```bash
mvn test
```

## Cenário atual de treinamento
O teste acessa a pagina de treino:
- `https://antoniotrindade.com.br/treinoautomacao/elementsweb.html`

E executa um fluxo simples:
1. encontra um campo de texto pelo atributo `name`
2. envia o valor `Hello World!!!`
3. valida se o valor foi preenchido corretamente

## Observacoes
Este repositorio tem objetivo educacional e pode ser expandido com novos cenarios de automacao (checkbox, radio button, select, alertas, etc.).
