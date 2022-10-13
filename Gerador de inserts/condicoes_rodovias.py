import chardet

def get_encoding(file_name):
    with open(file_name, 'rb') as f:
        return chardet.detect(f.read())['encoding']

def convert_to_float(value):
    temp_value = value.replace(',', '.')
    while temp_value[-1] == '.':
        temp_value = temp_value[:-1]
    return float(temp_value)

correct_encoding = get_encoding('./Data/icm_fev_2021.csv')

_file = open('./Data/icm_fev_2021.csv', 'r', encoding=f"{correct_encoding}")

first_line = _file.readline()[:-1][3:].split(';')

roads = set()

highways_parts = []

highways_parts_file = open('./SQLs/highways_parts.sql', 'w')

for line in _file:

    line = line[:-1].split(';')
    line[2] = convert_to_float(line[2])
    line[3] = convert_to_float(line[3])
    line[6] = convert_to_float(line[6])
    line[7] = convert_to_float(line[7])
    line[9] = convert_to_float(line[9])
    line[10] = convert_to_float(line[10])
    line[11] = convert_to_float(line[11])
    del line[4]
    del line[7]
    del line[5]
    del line[5]

    smallest_km = min(line[2], line[3])
    biggest_km = max(line[2], line[3])

    highways_parts_file.write(f"INSERT INTO TrechoRodovia values ('{line[0]}', '{line[1]}', {smallest_km}, {biggest_km}, TO_DATE('{line[4]}', 'DD/MM/YYYY'), {line[5]}, {line[6]}, {line[7]});\n")

    if (line[0], line[1]) not in roads:
        roads.add((line[0], line[1]))

roads = sorted(roads, key=lambda x: (x[0], x[1]))

roads_file = open('./SQLs/roads.sql', 'w')

for road in roads:
    roads_file.write(f"INSERT INTO rodovia values ('{road[0]}', '{road[1]}');\n")