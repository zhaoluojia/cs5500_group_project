import json


def main():
    calo_lst = []

    calo_lst.append(
        {"caloriesGoal": 10000, "startDate": "2013-10-23", "endDate": "2013-10-30"})
    calo_lst.append(
        {"caloriesGoal": 20000, "startDate": "2013-11-23", "endDate": "2013-11-30"})
    calo_lst.append(
        {"caloriesGoal": 30000, "startDate": "2013-10-03", "endDate": "2013-10-12"})
    calo_lst.append(
        {"caloriesGoal": 15000, "startDate": "2022-03-04", "endDate": "2022-03-10"})
    calo_lst.append(
        {"caloriesGoal": 10000, "startDate": "2013-05-23", "endDate": "2013-05-27"})
    calo_lst.append(
        {"caloriesGoal": 20000, "startDate": "2013-12-03", "endDate": "2013-12-10"})
    calo_lst.append(
        {"caloriesGoal": 30000, "startDate": "2013-10-04", "endDate": "2013-10-12"})
    calo_lst.append(
        {"caloriesGoal": 15000, "startDate": "2022-03-05", "endDate": "2022-03-14"})
    calo_lst.append(
        {"caloriesGoal": 30000, "startDate": "2013-10-04", "endDate": "2013-10-12"})
    calo_lst.append(
        {"caloriesGoal": 15000, "startDate": "2022-03-05", "endDate": "2022-03-14"})

    # Serializing json
    json_object = json.dumps(calo_lst, indent=4)

    # # Writing to sample.json
    with open("caloriesGoal.json", "w") as outfile:
        outfile.write(json_object)


main()
