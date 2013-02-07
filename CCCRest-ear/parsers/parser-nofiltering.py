import os, sys
import pyparsing as pyp
import errno
from lxml import etree
from os import walk, remove, stat
from os.path import join as joinpath
from md5 import md5

"""
Simple Parser for spin sequences

input:
path to folder that includes the sequences

output:
1) makes a new folder with "-xml" appended to the end of the sequences folder path
2) makes a new folder for each sequence file with the name of the sequence file name and
2) adds xml events parsed from the sequence file to the correct folder

Dependencies:  pyparsing, lxml

install pyparsing library
easy_install pyparsing

install lxml library
easy_install --allow-hosts=lxml.de,*.python.org lxml

"""

def main():
    print("command-line argument count:", len(sys.argv))
    print("command-line arguments:")
    print("    ", sys.argv)

    if not os.path.exists(sys.argv[1]+"-xml"):
        os.makedirs(sys.argv[1]+"-xml")


    os.chdir(sys.argv[1])

    for file in os.listdir("."):
        #parse all files that contain "Sequences" string  in their file name
        # and disregard .DS_Store files in mac platform
        if file !=".DS_Store" and file.find("Sequence"):
            print "filename: " + file
            parseFile(file)
    return 0


def parseFile(files):

    make_sure_path_exists(files)

    # define grammar for sequence file
    tagOriginator = pyp.Literal("<originator>") + pyp.Word(pyp.alphas) + pyp.Literal("</originator>")
    tagResponder = pyp.Literal("<responder>") + pyp.Word(pyp.alphas) + pyp.Literal("</responder>")
    tagType = pyp.Literal("<type>") + pyp.Word(pyp.alphas) + pyp.Literal("</type>")
    tagStatus = pyp.Literal("<status>") + pyp.Word(pyp.alphas)  + pyp.Literal("</status>")
    #tagEvent = pyp.Literal("<event>") +tagOriginator +tagResponder +  tagType +tagStatus + pyp.Literal("</event>")

    lineString = tagOriginator | tagResponder | tagType | tagStatus # | tagEvent
    f = open(files)
    lines = f.readlines()
    array = []
    for line in lines:
        #print line
        try:
            data = lineString.parseString(line)
            array.append(data)
        except pyp.ParseException as exception:
            print exception
    f.close()

    for element in array:
        print element
#
    for index, k in enumerate(range(0, len(array), 4)):
        print "%d: %d" % (index, k)
        print array[k]
        originator = array[k][1]    #originator tag
        responder = array[k + 1][1] #responder tag
        msgtype = array[k + 2][1]   #type tag
        status = array[k + 3][1]    #status tag

        print originator
        print responder
        print msgtype
        print status

        make_xml(originator, responder, msgtype, status).write(
            os.getcwd()+"-xml"+"/" + files + "/" + "event" + str(index + 1) + ".xml", pretty_print=True)

    return 0


def make_xml(originator, responder, msgtype, status):
    node = etree.Element('event')
    node1 = etree.SubElement(node, 'originator')
    node1.text = originator
    node2 = etree.SubElement(node, 'responder')
    node2.text = responder
    node3 = etree.SubElement(node, 'type')
    node3.text = msgtype
    node4 = etree.SubElement(node, 'status')
    node4.text = status
    doc = etree.ElementTree(node)
    return doc


def make_sure_path_exists(path):
    try:
        path = os.getcwd()+"-xml"+"/" + path
        print "check if dir exists: " + path
        if not os.path.exists(path):
            os.makedirs(path)
    except OSError as exception:
        if exception.errno != errno.EEXIST:
            raise

if __name__ == '__main__':
    main()
