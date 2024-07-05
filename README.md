# VertLog

## Swagger
![info](https://github.com/rodolfod2r2/VertLog/blob/Master/img/swagger.png) 
## Diagram
![info](https://github.com/rodolfod2r2/VertLog/blob/Master/img/vertlog.png)

## Descrição
VertLog é um sistema para gerenciamento de pedidos (buys) desenvolvido em Java com Spring Boot.

## Funcionalidades
- CRUD de pedidos de compra (buys).
- Upload e processamento de arquivos de pedidos.
- Consultas por identificador e por data dos pedidos.

## Tecnologias Utilizadas
- Java
- Spring Boot
- MongoDB
- Swagger (para documentação da API)
- Lombok (para redução de boilerplate)

## Pré-requisitos
- JDK 11 ou superior
- Maven
- MongoDB (em execução local ou configurado em ambiente de desenvolvimento)

## Configuração
1. Clone o repositório: `git clone https://github.com/rodolfod2r2/VertLog.git`
2. Importe o projeto em sua IDE como um projeto Maven.
3. Configure as propriedades do MongoDB no arquivo `application.properties`.
4. Execute o projeto Spring Boot.

## Como Usar
- Acesse a documentação da API Swagger para conhecer e testar os endpoints disponíveis.
- Utilize as funcionalidades CRUD para manipular pedidos.
- Faça upload de arquivos de pedidos no formato `.txt`.
- Utilizando a API Swagger a documentação da API Swagger pode ser acessada em http://localhost:8080/swagger-ui.html após iniciar o aplicativo.
- Upload de Arquivos o sistema suporta apenas upload de arquivos no formato .txt. Certifique-se de que o arquivo a ser enviado seja formatado corretamente para evitar erros no processamento.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests.

## Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
