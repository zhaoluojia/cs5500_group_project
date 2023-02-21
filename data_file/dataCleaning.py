#   private Long id;

#   private String exerciseName;
#   private Date date;
#   private Double duration;
#   private Double calories;
# exercise
import json


def main():
    f = open('storyline.json')
    # return json object as dictionary
    data = json.load(f)
    # create exercise dict
    exercise_lst = []
    # print(type(data[0]))
    for obj in data:
        # print(type(obj), obj["summary"])
        # for obj2 in obj["summary"]:
        #     print(type(obj2))
        #     break
        # break
        temp = {}
        # in summary
        if not obj or not obj["summary"]:
            continue

        for obj2 in obj["summary"]:
            temp["date"] = obj["date"]
            if obj2["activity"] in ["walking", "running", "cycling", "kayaking"]:
                temp["exerciseName"] = obj2["activity"]
                temp["duration"] = obj2["duration"]
                temp["calories"] = obj2["calories"]
        if temp:
            exercise_lst.append(temp.copy())
        temp = {}

    for i in range(len(exercise_lst)):
        if i > 3:
            break
        print(exercise_lst[i])
    # {'date': '20130210', 'exerciseName': 'walking', 'duration': 1670.0, 'calories': 59}
    # {'date': '20130211', 'exerciseName': 'walking', 'duration': 3552.0, 'calories': 203}
    # {'date': '20130212', 'exerciseName': 'running', 'duration': 180.0, 'calories': 27}

    # Serializing json
    json_object = json.dumps(exercise_lst, indent=4)

    # # Writing to sample.json
    with open("exercise.json", "w") as outfile:
        outfile.write(json_object)


main()
