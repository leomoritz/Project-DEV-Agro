# Project-DEV-Agro
Módulo 1 - Backend Java / Spring Boot - Projeto Avaliativo 2

Neste primeiro projeto você deve desenvolver a API de um sistema de gerenciamento de propriedades rurais chamado DEV-Agro.

A API deve possibilitar o cadastro de Empresas, Fazendas, Funcionários, Grãos.

O cadastro de empresas deve possibilitar o registro de nome, CNPJ e endereço.
Cada empresa pode ter uma ou mais propriedades rurais associadas (fazendas).
Cada empresa pode ter um ou mais funcionários associados.
Cada empresa pode ter um ou mais grãos associados.
O CNPJ deve ser validado para estar no formato: XX.XXX.XXX/XXXX-XX.

O cadastro de fazendas deve possibilitar o registro de nome, endereço, qual grão é produzido naquela propriedade, qual o estoque inicial daquele grão naquela fazenda (em kg), e qual empresa é proprietária da fazenda.
Cada fazenda pode ter apenas um grão associado.
Cada fazenda pode estar associada a apenas uma empresa.
Cada fazenda deve possuir um atributo para guardar a data da última colheita.

O cadastro de grãos deve possibilitar o registro de nome, empresa e tempo médio de colheita, em quantidade de dias.

O cadastro de funcionários deve possibilitar o registro de nome, sobrenome, CPF, endereço, telefone, sexo, data de nascimento, data de contratação, e qual empresa o emprega.
O CPF deve ser validado para estar no formato: XXX.XXX.XXX-XX.
O telefone deve ser validado para estar no formato: (XX) XXXXXXXXX.

Nesse primeiro momento, não é necessário fazer o front-end da aplicação, apenas o back-end, ou seja, a API (em Java com Spring Boot) e o banco de dados (PostgreSQL).
Você poderá testar sua aplicação utilizando ferramentas como Postman, Insomnia ou Curl.

Será necessário implementar os endpoints (controller, service, repository) para cadastro de todas essas entidades (models).

Na aplicação devem existir: 
-	Um endpoint para retornar a lista completa de empresas cadastradas.
-	Um endpoint que retorna a lista de fazendas de uma empresa.
-	Um endpoint que retorna a quantidade de fazendas de uma empresa.
-	Um endpoint que retorna uma lista de fazendas de uma empresa, onde cada elemento da lista deve ter 3 atributos: ID da fazenda, nome da fazenda e a data prevista da próxima colheita (considerando a data da última colheita e o tempo médio de colheita do grão associado a essa fazenda).
-	Um endpoint para registrar colheita em uma fazenda, que aumenta o estoque de grãos daquela fazenda.
-	Um endpoint para registrar retirada de grãos de uma fazenda, que diminui o estoque de grãos daquela fazenda.
-	Um endpoint que retorna a lista de grãos de uma empresa.
-	Um endpoint que retorna a lista de grãos associados a uma empresa, onde cada elemento da lista deve conter: nome do grão e quantidade em estoque, ordenado de menor para maior quantidade em estoque.
-	Um endpoint que retorna a lista de funcionários de uma empresa.
-	Um endpoint que retorna a quantidade de funcionários de uma empresa.
