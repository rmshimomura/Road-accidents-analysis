import datetime

import chardet

def get_encoding(file_name):
    with open(file_name, 'rb') as f:
        return chardet.detect(f.read())['encoding']

def convert_to_float(value):
    temp_value = value.replace(',', '.')
    while temp_value[-1] == '.':
        temp_value = temp_value[:-1]
    return float(temp_value)

correct_encoding = get_encoding('./Data/demostrativo_acidentes_cro.csv')

_file = open('./Data/demostrativo_acidentes_cro.csv', 'r', encoding=f"{correct_encoding}")

first_line = _file.readline()[:-1].split(';')

fatal_accidents_file = open('./SQLs/fatal_accidents.sql', 'w', encoding='utf-8')

not_fatal_accidents_file = open('./SQLs/not_fatal_accidents.sql', 'w', encoding='utf-8')

vehicles_on_accident_file_no_victims = open('./SQLs/vehicles_on_accident_no_victims.sql', 'w', encoding='utf-8')

vehicles_on_accident_file_with_victims = open('./SQLs/vehicles_on_accident_with_victims.sql', 'w', encoding='utf-8')

victims_on_accident_file = open('./SQLs/victims_on_accident.sql', 'w', encoding='utf-8')

available_vehicles = ['automovel', 'bicicleta', 'caminhao', 'moto', 'onibus', 'outros', 'tracao_animal', 'transporte_de_cargas_especiais', 'trator_maquinas', 'utilitarios']

casualities = ['ilesos', 'levemente_feridos', 'moderadamente_feridos', 'gravemente_feridos', 'mortos']

fatal_accidents = []

not_fatal_accidents = []

vehicles_on_accident_no_victims = []

vehicles_on_accident_with_victims = []

victims_on_accident = []

for line in _file:
    line = line[:-1].split(';')

    line[6] = line[6].strip()

    del line[2]
    del line[2]

    accident_date = line[0]

    accident_date = datetime.datetime.strptime(accident_date, '%d/%m/%Y')

    if accident_date < datetime.datetime(2021, 2, 1) or accident_date > datetime.datetime(2021, 2, 28):
        continue

    accident_date = line[0]

    accident_time = line[1]
    accident_km = line[2]
    road = line[3].split('/')

    accident_km = convert_to_float(accident_km)

    if len(road) == 1:
        continue

    _UF = road[1]
    road_name = road[0]
    way = line[4]
    accident_type = line[5]
    vehicles = line[6:16]
    victims = line[16:]

    vehicles = [int(x) if x != '' and x != ' ' else 0 for x in vehicles]
    victims = [int(x) if x != '' and x != ' ' else 0 for x in victims]


    if 1 in victims[1:]: # Ignore accidents with no victims

        if [_UF, road_name, accident_date, accident_time, accident_type, accident_km] not in fatal_accidents:
            fatal_accidents.append([_UF, road_name, accident_date, accident_time, accident_type, accident_km])
            fatal_accidents_file.write(f"INSERT INTO AcidenteComCasualidades(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, sentido, tipoDeAcidente) (SELECT '{_UF}', '{road_name}', kmInicial, kmFinal, dataAvaliacao, TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, '{way}', '{accident_type}' from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia limit 1);\n")

        for i in range(len(vehicles)):
            if vehicles[i] > 0:
                if [_UF, road_name, accident_date, accident_time, accident_type, accident_km, available_vehicles[i], vehicles[i]] not in vehicles_on_accident_with_victims:
                    vehicles_on_accident_with_victims.append([_UF, road_name, accident_date, accident_time, accident_type, accident_km, available_vehicles[i], vehicles[i]])
                    vehicles_on_accident_file_with_victims.write(f"INSERT INTO VeiculosNoAcidenteComCasualidades(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeVeiculo, quantidade)  (SELECT '{_UF}', '{road_name}', kmInicial, kmFinal, dataAvaliacao, TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, '{available_vehicles[i]}', {vehicles[i]} from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia limit 1);\n")

        for i in range(len(victims)):
            if victims[i] > 0:
                if [_UF, road_name, accident_date, accident_time, accident_type, accident_km, casualities[i], victims[i]] not in victims_on_accident:
                    victims_on_accident.append([_UF, road_name, accident_date, accident_time, accident_type, accident_km, casualities[i], victims[i]])
                    victims_on_accident_file.write(f"INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeCasualidade, quantidade)  (SELECT '{_UF}', '{road_name}', kmInicial, kmFinal, dataAvaliacao, TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, '{casualities[i]}', {victims[i]} from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia limit 1);\n")

    else:
        if [_UF, road_name, accident_date, accident_time, accident_type, accident_km] not in not_fatal_accidents:
            not_fatal_accidents.append([_UF, road_name, accident_date, accident_time, accident_type, accident_km])
            not_fatal_accidents_file.write(f"INSERT INTO AcidenteSemCasualidades(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, sentido, tipoDeAcidente) (SELECT '{_UF}', '{road_name}', kmInicial, kmFinal, dataAvaliacao, TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, '{way}', '{accident_type}' from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia limit 1);\n")

        for i in range(len(vehicles)):
            if vehicles[i] > 0:
                if [_UF, road_name, accident_date, accident_time, accident_type, accident_km, available_vehicles[i], vehicles[i]] not in vehicles_on_accident_no_victims:
                    vehicles_on_accident_no_victims.append([_UF, road_name, accident_date, accident_time, accident_type, accident_km, available_vehicles[i], vehicles[i]])
                    vehicles_on_accident_file_no_victims.write(f"INSERT INTO VeiculosNoAcidenteSemCasualidades(uf, nomeRodovia, kmInicial, kmFinal, dataAvaliacao, dataAcidente, horario, kmDoAcidente, nomeVeiculo, quantidade) (SELECT '{_UF}', '{road_name}', kmInicial, kmFinal, dataAvaliacao, TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, '{available_vehicles[i]}', {vehicles[i]} from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia limit 1);\n")
