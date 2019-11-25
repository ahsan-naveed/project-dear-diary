############## threat hunting ##############

import pandas as pd
import pprint

from collections import Counter
from malware_analysis import LOG_FILE
from malware_analysis import DISPOSITION
from malware_analysis import read_logs

pp = pprint.PrettyPrinter(indent=4)
dot = '.'


def get_data():
    """
    make lists of each category.
    :param:
    :return: a tuple of lists
    """
    logs = read_logs(LOG_FILE)
    data_unknown = []
    data_clean = []
    data_malicious = []

    for log in logs:
        if log['dp'] == DISPOSITION.UNKNOWN.value:
            data_unknown.append(log)
        elif log['dp'] == DISPOSITION.MALICIOUS.value:
            data_malicious.append(log)
        elif log['dp'] == DISPOSITION.CLEAN.value:
            data_clean.append(log)

    return (data_unknown, data_malicious, data_clean)


def count_extensions(data):
    """
    count occurences of extensions of files per category.
    :param data: list of log entries
    :return: list of tuples of 10 most common extensions
    """
    cnt = Counter()  # record occurences for each file extension

    for log in data:
        if len(log['nm']) > 0:
            extension = log['nm'].split('.')[1]
            cnt[extension] += 1

    return cnt.most_common(10)


def count_parent_folders(data):
    """
    count occurences of parent folders of files per category.
    :param data: list of log entries
    :return: list of tuples of 10 most common parent folders
    """
    cnt = Counter()

    for log in data:
        ph_arr = log['ph'].split('/')
        p_folder = ph_arr[-1]

        if dot in p_folder or len(p_folder) == 0:
            p_folder = ph_arr[-2]
        cnt[p_folder] += 1

    return cnt.most_common(10)


data_u, data_m, data_c = get_data()

df_m = pd.DataFrame(data_m)  # malicious
df_u = pd.DataFrame(data_u)  # unknown
df_c = pd.DataFrame(data_c)  # clean

mean_pt_malicious = df_m['pt'].mean()
mean_pt_clean = df_c['pt'].mean()
mean_pt_unknown = df_u['pt'].mean()

# avearge pt for each category is almost the same - bummer :(
print(f"""mean processing time for:

unknown: {mean_pt_unknown}
clean: {mean_pt_clean}
malicious: {mean_pt_malicious}""")

# some extensions are common b/w these two categories
count_extensions(data_m)
count_extensions(data_u)

# we can see parent folders of these two categories are very similar
count_parent_folders(data_m)
count_parent_folders(data_u)


"""
Final thoughts:
In order ot formalize our hypothesis we need to consider our IOCs:
Each log entry has following attritbutes:

ts:    timestamp - we compare timelines of each category and see which timeline the unknowns resemble the closest.
pt:    processing time - mean pt across all three categories was almost the same.
si:    session ID - this is unique to each log entry might not be as useful.
uu:    connector GUID - same as session ID.
bg:    business GUID - same as session ID.
sha:   sha 256 of the file - unique to each entry.
nm:    file name - I counted the extensions of files per category and was able to identify which .extensions were cuasing the most trouble we could add guards against such extensions in our cloud server.
ph:    path - The paths followed by seen MALICIOUS files and the UNKNOWN ones were suprisingly similar - this could be a strong indicator of compromise.
dp:    disposition (valid values: MALICIOUS (1), CLEAN (2), UNKNOWN (3))


Hypothesis:
Two types of folders seem to be causing most malicious activity i.e. folders with sports and astronomy information.
We can do statistical hypothesis testing to conform our hypothesis.
"""
