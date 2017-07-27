# TaskList

Este projeto é a abstração de um tasklist com cards que contenham título, descrição e data de criação.
O seu painel imita um Kanban. Ao arrastar o Card entre as colunas, seu status é atualizado.

Observações:
#####Por questao de tempo a edição do titulo e descrição não ficou pronta.
#####Assim como a apresentação da descrição também.

## Getting Started

Aqui seguem as configuração para execução local do projeto.

### Prerequisites

``Maven``

``Java 8``

### Installing

Realizar o maven clean install, logo em seguida maven update project.

## Running the tests

Não foi realizado teste em cima do CRUD da entidade Task.


## Deployment

Como não se sabe o ambiente em que o projeto será rodado. Foi colocado a anotação @CrossOrigin em alguns endpoints. Sabe-se que não é o correto. Apenas foi feito para facilitar a codificação.

A solução seria, adicionar o projeto frontend como arquivo estático em um apache ou nginx.
A aplicação backend executaria através do comando java -jar.
Haveria um apache mapeando os dois contextos e permitindo a comunicação, sem qualquer erro.

Teste local:
Suba o backend através da classe TaskListApp (método main).
E acesse o index.html 'na mão'.
Acredito que seja possível realizar o teste. Caso ainda dê problema, pode-se de maneira mais paliativa ser baixado um plugin do browser que permita o cors-domain.

Create table mysql:
##
	CREATE TABLE task (
		id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		title VARCHAR(20) NOT NULL,
		description VARCHAR(500),
		dateadded TIMESTAMP NOT NULL DEFAULT CURRENT_DATE(),
		status VARCHAR(10) NOT NULL
	);

## Authors

* **Lucas**

