import json


def main():
    user_lst = []

    user_lst.append(
        {"userName": "Ann", "password": "ann2233", "weight": "53"})

    user_lst.append(
        {"userName": "Bob", "password": "bobbb", "weight": "78"})

    user_lst.append(
        {"userName": "Cody", "password": "cww2995", "weight": "96"})
    user_lst.append(
        {"userName": "Larry", "password": "lol009", "weight": "83"})
    user_lst.append(
        {"userName": "Vivi", "password": "48fdfsgfr", "weight": "48"})
    user_lst.append(
        {"userName": "Nina", "password": "ojfldsf", "weight": "65"})
    user_lst.append(
        {"userName": "Andy", "password": "lefgw34", "weight": "59"})
    user_lst.append(
        {"userName": "Alex", "password": "keig34", "weight": "50"})
    user_lst.append(
        {"userName": "Evan", "password": "slkfdj432", "weight": "79"})
    user_lst.append(
        {"userName": "Annie", "password": "slfj3", "weight": "63"})

    # Serializing json
    json_object = json.dumps(user_lst, indent=4)

    # # Writing to sample.json
    with open("user.json", "w") as outfile:
        outfile.write(json_object)


main()
