import json
from datetime import datetime


def main():

    f = open(
        '/Users/anita/Desktop/NEU/Spring2023/CS5500/cs5500_group_project/dataCleaning/storyline.json')
    # return json object as dictionary
    data = json.load(f)
    # create exercise dict
    exercise_lst = []
    exercise_id = 0
    count = 0
    for obj in data:
        temp = {}
        # in summary
        if not obj or not obj["summary"]:
            continue
        for obj2 in obj["summary"]:
            # date formatting
            temp["_id"] = exercise_id
            temp["userId"] = 989
            # create date format
            dateS = obj["date"][0:4] + "-" + \
                obj["date"][4:6] + "-" + obj["date"][6:8]
            datetimeS = datetime.strptime(dateS, '%Y-%m-%d')
            # iso_date = datetime_obj.date().isoformat()
            # temp["date"] = iso_date
            # temp["date"] = {"$date": datetimeS}
            temp["date"] = {"$date": dateS}

            if obj2["activity"] in ["walking", "running", "cycling", "kayaking"]:
                temp["exerciseName"] = obj2["activity"]
                temp["duration"] = obj2["duration"]
                temp["calories"] = obj2["calories"]
            exercise_id += 1
        if temp:
            exercise_lst.append(temp.copy())
        temp = {}

    # testing
    # for i in range(len(exercise_lst)):
    #     if i > 3:
    #         break
    #     print(exercise_lst[i])
    startDate = datetime.strptime("20131023", '%Y%m%d')
    startdate = startDate.date().isoformat()

    endDate = datetime.strptime("20131030", '%Y%m%d')

    enddate = endDate.date().isoformat()

    # Serializing json
    bob_list = [{
        "_id": 989,
        "userName": "Bob",
        "password": "bobbbew433",
        "weight": 78.2,
        "caloriesGoal": {
            "_id": 200,
            "userId": 989,
            "caloriesGoal": 40000,
            "startDate": {"$date": "2013-10-23"},
            "endDate":  {"$date": "2013-10-30"}
        },
        "durationGoal": {
            "_id": 101,
            "userId": 989,
            "durationGoal": 20000,
            "startDate": {"$date": "2013-10-23"},
            "endDate":  {"$date": "2013-10-30"}
        },
        "exerciseList": exercise_lst}]
    json_object = json.dumps(bob_list, indent=4)
    # # Writing to sample.json
    with open("/Users/anita/Desktop/NEU/Spring2023/CS5500/cs5500_group_project/dataCleaning/bob.json", "w") as outfile:
        outfile.write(json_object)


main()
