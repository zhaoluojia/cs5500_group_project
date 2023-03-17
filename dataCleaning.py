import json
from datetime import datetime


def main():
    f = open('storyline.json')
    # return json object as dictionary
    data = json.load(f)
    # create exercise dict
    exercise_lst = []

    for obj in data:
        temp = {}
        # in summary
        if not obj or not obj["summary"]:
            continue
        for obj2 in obj["summary"]:
            # date formatting
            date = datetime.strptime(obj["date"], '%Y%m%d').date()
            temp["date"] = date.strftime('%Y-%m-%dT%H:%M:%S.%f%z')

            if obj2["activity"] in ["walking", "running", "cycling", "kayaking"]:
                temp["exerciseName"] = obj2["activity"]
                temp["duration"] = obj2["duration"]
                temp["calories"] = obj2["calories"]
        if temp:
            exercise_lst.append(temp.copy())
        temp = {}

    # testing
    # for i in range(len(exercise_lst)):
    #     if i > 3:
    #         break
    #     print(exercise_lst[i])
    startDate = datetime.strptime("20131023", '%Y%m%d').date()
    endDate = datetime.strptime("20131030", '%Y%m%d').date()
    # Serializing json
    bob_list = [{
        "username": "Bob",
        "passsword": "bobbbew433",
        "weight": 78.2,
        "caloriesGoal": {
            "caloriesGoal": 40000,
            "startDate": startDate.strftime('%Y-%m-%dT%H:%M:%S.%f%z'),
            "endDate":  endDate.strftime('%Y-%m-%dT%H:%M:%S.%f%z')
        },
        "durationGoal": {
            "durationGoal": 20000,
            "startDate": startDate.strftime('%Y-%m-%dT%H:%M:%S.%f%z'),
            "endDate":  endDate.strftime('%Y-%m-%dT%H:%M:%S.%f%z')
        },
        "exerciseList": exercise_lst}]
    json_object = json.dumps(bob_list, indent=4)
    # # Writing to sample.json
    with open("bob.json", "w") as outfile:
        outfile.write(json_object)


main()
