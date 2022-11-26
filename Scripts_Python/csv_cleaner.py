csv_file_path = "demostrativo_acidentes_viasul(1).csv"
no_of_lines = 4284

clean_file_path = "./limpos/clean_" + csv_file_path

csv_file = open(csv_file_path, "r")
clean_file = open(clean_file_path, "w")

first_line = csv_file.readline()
clean_file.write(first_line)

for line in csv_file.readlines()[no_of_lines:]:
    clean_file.write(line)





