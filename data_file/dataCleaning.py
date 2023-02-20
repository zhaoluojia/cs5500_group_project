#   private Long id;

#   private String exerciseName;
#   private Date date;
#   private Double duration;
#   private Double calories;
# exercise
import json


def changeDateFormat(oldDate):
    date = ""
    for i, char in enumerate(oldDate):
        date += char
        if i == 3 or i == 5:
            date += "-"
    return date


def main():
    f = open('../storyline.json')
    # return json object as dictionary
    data = json.load(f)
    # create exercise dict
    exercise_lst = []
    userA = {}
    # print(type(data[0]))
    for obj in data:
        # print(type(obj), obj["summary"])
        # for obj2 in obj["summary"]:
        #     print(type(obj2))
        #     break
        # break
        # in summary
        if not obj or not obj["summary"]:
            continue

        temp = {}
        # start to make object
        for obj2 in obj["summary"]:
            temp["date"] = changeDateFormat(obj["date"])
            if obj2["activity"] in ["walking", "running", "cycling", "kayaking"]:
                temp["exerciseName"] = obj2["activity"]
                temp["duration"] = obj2["duration"]
                temp["calories"] = obj2["calories"]
        if temp:
            exercise_lst.append(temp.copy())
        temp = {}
    userA["username"] = "Bob"
    userA["passsword"] = "bobbbew433"
    userA["weight"] = 78.20
    userA["caloriesGoal"] = {
        "caloriesGoal": 40000, "startDate": "2013-10-23", "endDate": "2013-10-30"}
    userA["durationGoal"] = {
        "durationGoal": 20000, "startDate": "2013-11-23", "endDate": "2013-11-30"}
    userA["exerciseList"] = exercise_lst

    # print(userA["exerciseList"])
    # {'date': '20130210', 'exerciseName': 'walking', 'duration': 1670.0, 'calories': 59}
    # {'date': '20130211', 'exerciseName': 'walking', 'duration': 3552.0, 'calories': 203}
    # {'date': '20130212', 'exerciseName': 'running', 'duration': 180.0, 'calories': 27}

    # Serializing json
    json_object = json.dumps(userA, indent=4)

    # # Writing to sample.json
    with open("userA.json", "w") as outfile:
        outfile.write(json_object)


main()
