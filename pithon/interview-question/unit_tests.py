import unittest

# functions
from malware_analysis import find_fictitious
from malware_analysis import read_logs
from malware_analysis import create_connection
from malware_analysis import select_all_malware

# constants
from malware_analysis import LOG_FILE
from malware_analysis import DB_FILE
from malware_analysis import TABLE


class MalwareAnalysisTestCase(unittest.TestCase):
    """ Tests for malware_analysis.py. """

    def test_find_fictitious(self):
        data = [{
            'ts': 1480976958,
            'pt': 42,
            'si': '465635de-893d-4691-93bd-47ab5c24f4a4',
            'uu': '022ec83c-c6ba-44c4-8533-7c4108e61e74',
            'bg': '2813914c-1464-42b7-b571-a1b4a4eb07ec',
            'sha': '1839632018638fba1e2272e7cbcf049994a5a7765251f3acbd3806c839f445af',
            'nm': 'Lotstring.key',
            'ph': '/soccer/15EZTURZ70P5C7JUK/Litte Dipper/Lotstring.key',
            'dp': 3
        }]
        fictitious_sha = find_fictitious(data)[0]
        self.assertEqual(
            fictitious_sha,
            "1839632018638fba1e2272e7cbcf049994a5a7765251f3acbd3806c839f445af",
            "fictitious malware filtered correctly.")

    def test_read_logs(self):
        logs = read_logs(LOG_FILE)
        self.assertGreater(
            len(logs),
            1,
            "logs read successfully.")
    
    def test_create_connection(self):
        conn = create_connection(DB_FILE)
        cur = conn.cursor()
        logs = cur.execute(f'SELECT * FROM {TABLE} LIMIT 1;').fetchall()
        self.assertEqual(
            len(logs),
            1,
            "read operation successful."
        )
    
    def test_select_all_malware(self):
        conn = create_connection(DB_FILE)
        cur = conn.cursor()
        seen_malware_size = len(cur.execute(f'SELECT * FROM {TABLE};').fetchall())
        select_all_malware_size = len(select_all_malware(conn))
        self.assertEqual(
            seen_malware_size,
            select_all_malware_size,
            f'Whole {TABLE} read correctly.'
        )




if __name__ == "__main__":
    unittest.main()
