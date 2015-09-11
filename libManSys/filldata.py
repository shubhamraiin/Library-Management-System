
# File is a part of the database course projec

import MySQLdb
import csv
import re
import sys

def fill_authors_books():
    db = MySQLdb.connect(host="127.0.0.1",user="root",db="library")
    cur = db.cursor()
    exceptions = []

    with open("F:/Library_data_2/books_authors.csv") as authors:
        authors = csv.reader(authors, delimiter="\t")

        for line in authors:
            book_id = line[0]
            auth_names = line[1]
            book_name = line[2]

            # In order to avoid inserting the header row in db
            if book_id == "book_id":
                continue

            try:
                cur.execute("""insert into book values (%s, %s)""", (book_id, book_name))
                db.commit()
            except:
                db.rollback()

            for parts in auth_names.split(','):
                parts = parts.strip()
                full_name = parts

                pattern = re.compile('\s*[. ]\s*')
                parts = pattern.split(parts)

                #Deal with exceptions:
                if len(parts) > 3:
                    # TOO long a name
                    exceptions.append(line) 
                elif len(parts) == 2:
                    fname = parts[0]
                    lname = parts[1]
                    minit = None
                elif len(parts) == 1:
                    fname = parts[0]
                    lname = None
                    minit = None
                else: # A GOOD NAME!!
                    fname = parts[0]
                    minit = parts[1]
                    lname = parts[2]

                #end inner for loop
            try:
                cur.execute("""insert into books_authors values (%s, %s, %s, %s, %s)""",(book_id, full_name, fname, minit, lname))
                db.commit()
            except:
                db.rollback()
    print exceptions
    db.close()


def fill_library_branch():
    db = MySQLdb.connect(host="127.0.0.1",user="root",db="library")
    cur = db.cursor()
    exceptions = []

    with open("F:/Library_data_2/library_branch.csv") as data:
        data = csv.reader(data, delimiter="\t")

        for line in data:
            branch_id = line[0]
            branch_name = line[1]
            address = line[2]

            # ignore the header
            if branch_id == "branch_id":
                continue
            try:
                cur.execute("""insert into library_branch values (%s, %s, %s)""", (branch_id, branch_name, address))
                db.commit()
            except:
                db.rollback()

def fill_book_copies():
    db = MySQLdb.connect(host="127.0.0.1",user="root",db="library")
    cur = db.cursor()
    exceptions = []

    with open("F:/Library_data_2/book_copies.csv") as copies:
        copies = csv.reader(copies, delimiter="\t")

        for line in copies:
            book_id = line[0]
            branch_id = line[1]
            num_copies = line[2]
            # ignore the header
            if book_id == "book_id":
                continue
            try:
                cur.execute("""insert into book_copies values (%s, %s, %s)""", (book_id, branch_id, num_copies))
                db.commit()
            except:
                db.rollback()

def fill_borrowers():
    db = MySQLdb.connect(host="127.0.0.1",user="root",db="library")
    cur = db.cursor()
    exceptions = []

    with open("F:/Library_data_2/borrowers.csv") as data:
        data = csv.reader(data, delimiter="\t")

        for line in data:
            card_no = line[0]
            fname = line[1]
            lname = line[2]
            address = line[3]+" "+line[4]+" "+line[5]
            phone = line[6]

            # ignore the header
            if card_no == "card_no":
                continue
            try:
                cur.execute("""insert into borrowers values (%s, %s, %s, %s, %s)""", (card_no, fname, lname, address, phone))
                db.commit()
            except:
                db.rollback()

if __name__ == "__main__":
    fill_authors_books()
    fill_library_branch()
    fill_book_copies()
    fill_borrowers()