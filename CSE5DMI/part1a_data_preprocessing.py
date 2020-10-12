import pandas as pd

csv_filename = 'winequality-RED-v2.csv'
# import csv file
d = pd.read_csv(csv_filename)

# fill empty values with mean of the attribute

# using https://pandas.pydata.org/pandas-docs/stable/user_guide/missing_data.html#filling-with-a-pandasobject
# d = d.fillna(d.mean())

meanDensity = d["density"].mean()
missingDensity = pd.isna(d["density"])
d["density"].where(~ missingDensity, meanDensity, inplace=True)

meanChlorides = d["chlorides"].mean()
missingChlorides = pd.isna(d["chlorides"])
d["chlorides"].where(~ missingChlorides, meanChlorides, inplace=True)

# remanme quality column in export to import as discrete value in orange3
# https://orange3.readthedocs.io/projects/orange-visual-programming/loading-your-data/#header-with-attribute-type-information
d = d.rename(columns={"quality": "D#quality"})

# export processed csv
d.to_csv(csv_filename[:-4]+'_processed.csv', index=False)