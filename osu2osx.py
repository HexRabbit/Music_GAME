#!/usr/bin/env python3

import sys, json, re

if len(sys.argv) == 1:
    print('usage: trans.py [osufile...]')
    sys.exit()

osufiles = sys.argv[1:]

def getElement(line, name):
    if line.startswith(name):
        return line.split(':')[1].strip()

for osufile in osufiles:
    with open(osufile, 'r') as f:

        audiofile = ''
        title = ''
        artist = ''
        hitnotes = []

        for line in f:
            if line.startswith('AudioFilename'):
                audiofile = line.split(':')[1].strip()
                break

        for line in f:
            if line.startswith('Title'):
                title = line.split(':')[1].strip()
                break

        for line in f:
            if line.startswith('Artist'):
                artist = line.split(':')[1].strip()
                break

        for line in f:
            if line.startswith('[HitObjects]'):
                break

        for line in f:
            cw = 512 // 4
            note = []
            params = re.split('\W+', line)

            note.append(int(params[0]) // cw)      # column
            note.append(int(params[2]))            # start
            if int(params[3]) & 128:
                note.append(int(params[5]))            # end
            else:
                note.append(0)
            note.append((int(params[3]) & 128) >> 7)  # hold

            hitnotes.append(note)

        beatmap = {
            'audiofile': audiofile,
            'title': title,
            'artist': artist,
            'hitnotes': hitnotes
        }

        with open(osufile.replace('.osu','.osx'), 'w') as nf:
            nf.write(json.dumps(beatmap, sort_keys=True, indent=2))


