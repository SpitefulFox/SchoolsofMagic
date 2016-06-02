def main():
    print("Please enter filename:")
    filename = input()
    f = open("../src/main/resources/assets/schoolsmagic/models/item/" + filename + '.json', mode='xt')

    f.write('{\n')
    f.write('  "parent": "schoolsmagic:item/base_item",\n')
    f.write('  "textures": {\n')
    f.write('    "layer0": "schoolsmagic:items/' + filename + '"\n')
    f.write('  }\n')
    f.write('}')
    f.close()

main()
