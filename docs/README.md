## CineQuest
Por `Miguel Avila de Oliveira` do curso de `Sistemas de Informação` da `Universidade Federal de Santa Maria`

## Proposta
A ideia proposta é criar uma plataforma de catalogação de filmes gamificada. A inspiração inicial foi o [Letterboxd](https://letterboxd.com/), porém com a inserção de elementos oriundos de *games* para incentivar o usuário a usar o aplicativo. Entre as features propostas estão a catalogação de *reviews* de filmes, inserção de filmes no banco de dados, loja de pontos, quests e conquistas.

## Desenvolvimento

#### Documentação 
Inicialmente eu planejava documentar tudo em vários arquivos separados para facilitar a escrita do README e etc, mas com o passar do tempo eu fui deixando de lado a ideia por que eu ia desenvolvendo "no pique" e quando via já tinha várias coisas implementadas.
#### Arquitetura do Sistema
Durante o curso técnico, já tinha visto um pouco da arquitetura MVC com Python. Após pesquisar maneiras de organizar o projeto acabei me deparando com o MVC, e por já ter uma breve experiência, decidi utilizar para trabalhar com Java. Foi um pouco diferente, precisei fazer algumas adaptações no que eu lembrava, mas nada demais. Ficou bem tranquilo de arrumar os problemas do servidor pois já sabia exatamente as responsabilidades de cada Classe. 
#### Interface
Como o foco do trabalho era o trabalho com Java e Orientação a Objetos, optei por fazer tudo (ou pelo menos 90%) do frontend com IA (ChatGPT, para ser mais preciso). Fiz esta escolha pois no primeiro trabalho eu demorei mais tempo desenvolvendo uma interface do zero do que trabalhando com Haskell propriamente dito, e como eu já sabia que Java era uma linguagem muito mais verbosa e que eu teria que escrever muito código, preferi aproveitar para aprofundar o conhecimento no que mais importava. Gostaria de deixar claro também que eu utilize a ajuda de IA em certos pontos do backend, principalmente nos problemas que eu estava tendo com o deploy no Render, na construção do Dockerfile (que eu não conhecia antes) e na escolha de bibliotecas.
#### Erros e Problemas
Diferentemente do trabalho 1, o segundo me gerou **vários** problemas e erros. O principal erro, que me perseguiu por mais de uma semana, a ponto de eu ignorá-lo e continuar construindo as outras partes do código mesmo sabendo que daria problema no deploy, foi relacionado à URL do Banco de Dados. Como eu hospedei um postgres no render, eu tinha as URLS para acessar o banco. Eu adicionei no meu projeto do Render uma Variável de Ambiente DATABASE_URL com o URL Externo no banco. Localmente, com a minha variável DATABASE_URL eu conseguia acessar o banco, mas no Render não. Apenas hoje (poucas horas antes da entrega final) eu consegui descobrir o motivo, e era o seguinte: ao colar o url do banco na variável de ambiente do Render, o último caractere dela era uma quebra de linha (\n), mas visualmente era invisível, fazendo com que eu não conseguisse conectar no banco.<br>
Também tive alguns probleminhas com as requisições HTTP retornando códigos de erro quando pareciam corretas, mas geralmente eram problemas nas queries SQL ou no tipo de retorno dos meus métodos.


