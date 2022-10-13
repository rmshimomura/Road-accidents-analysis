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

correct_encoding = get_encoding('./Data/demostrativo_acidentes_aco.csv')

_file = open('./Data/demostrativo_acidentes_aco.csv', 'r', encoding=f"{correct_encoding}")

first_line = _file.readline()[:-1].split(';')

fatal_accidents_file = open('./SQLs/fatal_accidents.sql', 'w')

not_fatal_accidents_file = open('./SQLs/not_fatal_accidents.sql', 'w')

vehicles_on_accident_file_no_victims = open('./SQLs/vehicles_on_accident_no_victims.sql', 'w')

vehicles_on_accident_file_with_victims = open('./SQLs/vehicles_on_accident_with_victims.sql', 'w')

victims_on_accident_file = open('./SQLs/victims_on_accident.sql', 'w')

available_vehicles = ['automovel', 'bicicleta', 'caminhao', 'moto', 'onibus', 'outros', 'tracao_animal', 'transporte_de_cargas_especiais', 'trator_maquinas', 'utilitarios']

casualities = ['ilesos', 'levemente_feridos', 'moderadamente_feridos', 'gravemente_feridos', 'mortos']

for line in _file:
    line = line[:-1].split(';')

    line[6] = line[6].strip()

    del line[2]
    del line[2]

    accident_date = line[0]

    accident_date = datetime.datetime.strptime(accident_date, '%d/%m/%Y')

    if accident_date < datetime.datetime(2021, 2, 1) or accident_date > datetime.datetime(2021, 2, 28):
        continue

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

        fatal_accidents_file.write(f"INSERT INTO AcidenteComCasualidades values ('{_UF}', '{road_name}', (SELECT kmInicial, kmFinal, dataAvaliacao from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia), TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, '{way}', '{accident_type}')\n")

        for i in range(len(vehicles)):
            if vehicles[i] > 0:
                vehicles_on_accident_file_with_victims.write(f"INSERT INTO VeiculosNoAcidenteComCasualidades values ('{_UF}', '{road_name}', (SELECT kmInicial, kmFinal, dataAvaliacao from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia), TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, {accident_type}, '{available_vehicles[i]}', {vehicles[i]})\n")

        for i in range(len(victims)):
            if victims[i] > 0:
                victims_on_accident_file.write(f"INSERT INTO AcidenteComCasualidadeEnvolveTipoCasualidade values ('{_UF}', '{road_name}', (SELECT kmInicial, kmFinal, dataAvaliacao from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia), TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, {accident_type}, '{casualities[i]}', {victims[i]})\n")

    else:
        not_fatal_accidents_file.write(f"INSERT INTO AcidenteSemCasualidades values ('{_UF}', '{road_name}', (SELECT kmInicial, kmFinal, dataAvaliacao from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia), TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, '{way}', '{accident_type}')\n")

        for i in range(len(vehicles)):
            if vehicles[i] > 0:
                vehicles_on_accident_file_no_victims.write(f"INSERT INTO VeiculosNoAcidenteSemCasualidades values ('{_UF}', '{road_name}', (SELECT kmInicial, kmFinal, dataAvaliacao from TrechoRodovia WHERE {accident_km} >= kmInicial and {accident_km} < kmFinal and '{_UF}' = UF and '{road_name}' = nomeRodovia), TO_DATE('{accident_date}', 'DD/MM/YYYY'), '{accident_time}', {accident_km}, {accident_type}, '{available_vehicles[i]}', {vehicles[i]})\n")