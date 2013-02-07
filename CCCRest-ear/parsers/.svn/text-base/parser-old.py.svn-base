import os, sys
import pyparsing as pyp
import itertools
import errno
from lxml import etree

"""
Simple Parser for spin sequences

input:
path to folder that includes the sequences

output:
1) makes a new folder with the name of the sequence file name and
2) adds xml events parsed from the sequence file to the correct folder

Dependencies: pyparsing, lxml

install pyparsing library
easy_install pyparsing

install lxml library
easy_install --allow-hosts=lxml.de,*.python.org lxml

"""

def main():
    print("command-line argument count:", len(sys.argv))
    print("command-line arguments:")
    print("    ", sys.argv)

    #  os.listdir(sys.argv[1])

    os.chdir(sys.argv[1])

    for files in os.listdir("."):
        #if files.endswith(".txt"):
        print "filename: " + files
        parseFile(files)
    return 0


def parseFile(files):
    # define grammar for sequence file
    make_sure_path_exists(files)

    sendReceive = pyp.Word("Sent") | pyp.Word("Sent") | pyp.Word("Recv")
    #sendReceive = pyp.OneOrMore("Send","Sent","Recv")
    messageType = pyp.delimitedList(pyp.Word(pyp.alphas), delim=',')
    arrows = pyp.Literal("->") | pyp.Literal("<-")

    lineString = pyp.Suppress(pyp.Word(pyp.nums + ":")) + pyp.Suppress("proc") + pyp.Suppress(
        pyp.Word(pyp.nums)) + pyp.Suppress(
        pyp.Literal("(")) + pyp.Word(pyp.alphas) + pyp.Suppress(pyp.Literal(")")) + pyp.Suppress("line") + pyp.Suppress(
        pyp.Word(pyp.nums)) + pyp.Suppress(
        pyp.quotedString) + sendReceive + messageType + pyp.ZeroOrMore(arrows)

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

    for index, k in enumerate(range(0, len(array), 2)):
        print "%d: %d" % (index, k)
        print array[k]
        originator = array[k][0]
        print array[k+1]
        responder = array[k+1][0]
        msgtype = array[k+1][2]
        make_xml(originator, responder,msgtype).write(os.getcwd()+ files +"/" + "event" + str(index+1) + ".xml", pretty_print=True)

    return 0


def make_xml(originator, responder, msgtype):
    node = etree.Element('event')
    node1 = etree.SubElement(node, 'originator')
    node1.text = originator
    node2 = etree.SubElement(node, 'responder')
    node2.text = responder
    node3 = etree.SubElement(node, 'type')
    node3.text = msgtype
    node4 = etree.SubElement(node, 'status')
    node4.text = 'success'
    doc = etree.ElementTree(node)
    return doc


def make_sure_path_exists(path):
    try:
        path = os.getcwd() + path
        print "check if dir exists: " + path
        if not os.path.exists( path):
            os.makedirs(path)
    except OSError as exception:
        if exception.errno != errno.EEXIST:
            raise

if __name__ == '__main__':
    main()