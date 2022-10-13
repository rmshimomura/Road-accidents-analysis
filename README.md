# Road-accidents-analysis

## Apresentação
Este repositório é referente ao conteúdo produzido para o trabalho de Sistema de análise de dados abertos da disciplina de Banco de Dados I. Será feito um sistema que analisará conjuntos de dados diferentes e, a partir da associação entre seus dados, produzirá relatórios. 

Decidimos escolher e analisar tabelas de dados sobre as condições das rodovias federais, bem como acidentes ocorridos, visto que o transporte rodoviário predomina na matriz de transporte brasileira, podendo, assim, gerar análises socialmente relevantes.  

## Conjuntos de dados:
Foram escolhidos um conjuntos de dados referentes ao estado de manutenção das rodovias federais e um relativo aos acidentes em rodovias.

- Dataset de acidentes: https://dados.antt.gov.br/dataset/acidentes-rodovias
- Dataset das condições de manutenção das rodovias: https://dados.gov.br/dataset/condicoes-do-pavimento1

## Decisões do projeto:
Decidimos apagar as seguintes colunas (do .csv de origem):

- Tabela dos acidentes:
    - n_da_ocorrencia - Não é relevante para a análise pois é um valor interno de cada concessionária que reportou o acidente
    - tipo_de_ocorrencia - Retiramos pois não é padronizado (vamos usar os dados das casualidades)
    - Transformamos as colunas referentes aos veículos em uma relação separada, portanto, as colunas de veículos (carro, moto, bicicleta, tração animal) serão tratadas no backend.
    - Separamos também as colunas referentes as casualidades em uma relação separada, portanto, sendo tratadas no backend.

- Tabela das condições da rodovia:
    - Extensão (km) - Não é relevante pois pode ser calculado facilmente caso seja necessário
    - Latitude - Não é relevante pois não será utilizado no frontend
    - Longitude - Não é relevante pois não será utilizado no frontend
    - Observação - Os datasets possuem em sua maioria observações vazias gerando assim muitos registros nulos. Além disso, as poucas observações que possuem dados irrelevantes para a análise e não padronizados. 



Updates do projeto: 

- 12/10/2022 - Tiramos a relação de acidentes e deixamos apenas suas subclasses devido à participação total da superclasse nas subclasses. (todo acidente possui ou não casualidades, por exemplo).

## Links

- Modelo Relacional: https://github.com/rmshimomura/Road-accidents-analysis/blob/development/Modelos/Modelo_Relacional.pdf
- Diagrama entidade relacionamento: https://github.com/rmshimomura/Road-accidents-analysis/blob/development/Modelos/Modelo%20entidade%20relacionamento.png

## Relatórios a serem produzidos

- Relação entre acidentes e condições da rodovia onde ocorreram
- Condição média das rodovias federais para determinado estado
- Quantidade de veículos envolvidos em acidentes em determinada rodovia, distinguindo-os também pelo seu tipo
- Taxa de mortalidade em acidentes para determinada rodovia
- Tipo mais comum de acidente em determinada rodovia
