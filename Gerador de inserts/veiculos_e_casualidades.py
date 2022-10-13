vehicles_file = open('./SQLs/veiculos.sql', 'w')

available_vehicles = ['automovel', 'bicicleta', 'caminhao', 'moto', 'onibus', 'outros', 'tracao_animal', 'transporte_de_cargas_especiais', 'trator_maquinas', 'utilitarios']

for vehicle_name in available_vehicles:
    vehicles_file.write(f"INSERT INTO Veiculo VALUES ('{vehicle_name}');\n")

casualities_file = open('./SQLs/tipoDeCasualidade.sql', 'w')

casualities = ['ilesos', 'levemente_feridos', 'moderadamente_feridos', 'gravemente_feridos', 'mortos']

for casuality in casualities:
    casualities_file.write(f"INSERT INTO TipoDeCasualidade VALUES ('{casuality}');\n")