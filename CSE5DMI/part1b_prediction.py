from Orange.data import Table, Domain
from Orange.classification import SklTreeLearner
from Orange.evaluation import CrossValidation, scoring

file_name = 'winequality-RED-v2_processed.csv'
data_tab = Table.from_file(file_name)

feature_vars = list(data_tab.domain.variables[:-1])
class_label_var = data_tab.domain.variables[-1]
winequality_domain = Domain(feature_vars, class_label_var)
data_tab = Table.from_table(domain=winequality_domain, source=data_tab)

# segregating 80% data for training and rest for testing
data_tab.shuffle()

test_data_tab = data_tab[int(len(data_tab)*0.8):]
train_data_tab = data_tab[:int(len(data_tab)*0.8)]

tree_learner = SklTreeLearner()
decision_tree = tree_learner(train_data_tab)

def decision_tree_predict(d_tree, input_data):
    predicted_label_vals = d_tree(input_data)
    predicted_labels = []
    for val in predicted_label_vals:
        predicted_labels.append(input_data.domain.class_var.values[int(val)])
    return predicted_labels

predicted_class_labels = decision_tree_predict(decision_tree, test_data_tab[:])

# calculating confusion matrix
num_of_test_samples = len(predicted_class_labels)
num_of_correct_5s = 0
num_of_correct_7s = 0
num_of_false_5s = 0
num_of_false_7s = 0
num_of_correct_predictions = 0

for i in range(num_of_test_samples):
    if predicted_class_labels[i] == test_data_tab[i, test_data_tab.domain.class_var]:
        num_of_correct_predictions += 1
    if predicted_class_labels[i] == '5':
        if test_data_tab[i, test_data_tab.domain.class_var] == '5':
            num_of_correct_5s += 1
        else:
            num_of_false_5s += 1
    elif predicted_class_labels[i] == '7':
        if test_data_tab[i, test_data_tab.domain.class_var] == '7':
            num_of_correct_7s += 1
        else:
            num_of_false_7s += 1

# Cross validate with test data
eval_results = CrossValidation(data_tab, [tree_learner], k=10)
print("Accuracy: {:.3f}".format(scoring.CA(eval_results)[0]))
print("AUC: {:.3f}".format(scoring.AUC(eval_results)[0]))


accuracy = num_of_correct_predictions / num_of_test_samples
print('Self test Accuracy = {:.3f}'.format(accuracy))
print('Confusion Matrix: ')
print(num_of_correct_5s,num_of_correct_7s,num_of_false_5s,num_of_false_7s)
