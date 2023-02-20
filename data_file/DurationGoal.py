import json


def main():
    du_lst = []

    du_lst.append(
        {"durationGoal": 10000, "startDate": "2013-10-23", "endDate": "2013-10-30"})
    du_lst.append(
        {"durationGoal": 20000, "startDate": "2013-11-23", "endDate": "2013-11-30"})
    du_lst.append(
        {"durationGoal": 30000, "startDate": "2013-10-03", "endDate": "2013-10-12"})
    du_lst.append(
        {"durationGoal": 15000, "startDate": "2022-03-04", "endDate": "2022-03-10"})
    du_lst.append(
        {"durationGoal": 10000, "startDate": "2013-10-23", "endDate": "2013-10-30"})
    du_lst.append(
        {"durationGoal": 20000, "startDate": "2013-11-23", "endDate": "2013-11-30"})
    du_lst.append(
        {"durationGoal": 30000, "startDate": "2013-10-03", "endDate": "2013-10-12"})
    du_lst.append(
        {"durationGoal": 15000, "startDate": "2022-03-04", "endDate": "2022-03-10"})
    du_lst.append(
        {"durationGoal": 30000, "startDate": "2013-10-03", "endDate": "2013-10-12"})
    du_lst.append(
        {"durationGoal": 15000, "startDate": "2022-03-04", "endDate": "2022-03-10"})

    # Serializing json
    json_object = json.dumps(du_lst, indent=4)

    # # Writing to sample.json
    with open("durationGoal.json", "w") as outfile:
        outfile.write(json_object)


main()
