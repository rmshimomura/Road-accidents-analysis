INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade, quantidade)  (SELECT 'RJ', 'BR-040', kmInicial, kmFinal, dataAvaliacao, TO_DATE('11/01/2022', 'DD/MM/YYYY'), '23:48:00', 11.0, 'levemente_feridos', 1 from TrechoRodovia WHERE 11.0 >= kmInicial and 11.0 < kmFinal and 'RJ' = UF and 'BR-040' = nomeRodovia limit 1);
INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade, quantidade)  (SELECT 'RJ', 'BR-040', kmInicial, kmFinal, dataAvaliacao, TO_DATE('08/01/2022', 'DD/MM/YYYY'), '14:00:00', 8.0, 'moderadamente_feridos', 1 from TrechoRodovia WHERE 8.0 >= kmInicial and 8.0 < kmFinal and 'RJ' = UF and 'BR-040' = nomeRodovia limit 1);
INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade, quantidade)  (SELECT 'RJ', 'BR-040', kmInicial, kmFinal, dataAvaliacao, TO_DATE('08/01/2022', 'DD/MM/YYYY'), '14:00:00', 8.0, 'gravemente_feridos', 1 from TrechoRodovia WHERE 8.0 >= kmInicial and 8.0 < kmFinal and 'RJ' = UF and 'BR-040' = nomeRodovia limit 1);
INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade, quantidade)  (SELECT 'RJ', 'BR-040', kmInicial, kmFinal, dataAvaliacao, TO_DATE('29/01/2022', 'DD/MM/YYYY'), '16:46:00', 7.0, 'levemente_feridos', 1 from TrechoRodovia WHERE 7.0 >= kmInicial and 7.0 < kmFinal and 'RJ' = UF and 'BR-040' = nomeRodovia limit 1);
INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade, quantidade)  (SELECT 'RJ', 'BR-040', kmInicial, kmFinal, dataAvaliacao, TO_DATE('30/01/2022', 'DD/MM/YYYY'), '10:05:00', 7.0, 'moderadamente_feridos', 1 from TrechoRodovia WHERE 7.0 >= kmInicial and 7.0 < kmFinal and 'RJ' = UF and 'BR-040' = nomeRodovia limit 1);
INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade, quantidade)  (SELECT 'RJ', 'BR-040', kmInicial, kmFinal, dataAvaliacao, TO_DATE('02/01/2022', 'DD/MM/YYYY'), '09:29:00', 4.0, 'moderadamente_feridos', 1 from TrechoRodovia WHERE 4.0 >= kmInicial and 4.0 < kmFinal and 'RJ' = UF and 'BR-040' = nomeRodovia limit 1);