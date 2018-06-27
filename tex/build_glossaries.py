#! /usr/bin/env python3
"""
BSD 3-Clause License

Copyright (c) 2018, Daniel Schäfer
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of the copyright holder nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
"""
import sys

"""
Converts a simple file like

```
ACPI
Advanced Configuration and Power Interface

BIOS
Basic Input/Output System
BIOSes
```

to

```
\newglossaryentry{ACPI} {
  name        = {ACPI}
  description = {Advanced Configuration and Power Interface}
}


\newglossaryentry{BIOS} {
  name        = {BIOS}
  plural      = {BIOSes}
  description = {Basic Input/Output System}
}
```
"""

def formatEntry(header, description, plural):
    return "".join((
        "\\newglossaryentry{{{}}} {{\n".format(header),
        "  name        = {{{}}},\n".format(header),
        "  plural      = {{{}}},\n".format(plural) if plural is not None else "",
        "  description = {{{}}}\n".format(description),
        "}\n"
    ))

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("build_glossaries.py [input]", file=sys.stderr)
        sys.exit(1)

    with open(sys.argv[1], 'r') as r:
        i = 0
        header = None
        description = None
        plural = None
        for line in r:
            line = line.strip()
            i += 1
            if i == 1:
                if not line:
                    print("Invalid glossaries entry (too short)", file=sys.stderr)
                    sys.exit(1)
                header = line
            elif i == 2:
                if not line:
                    print("Invalid glossaries entry (too short)", file=sys.stderr)
                    sys.exit(1)
                description = line
            elif i == 3:  # Skip empty line separator
                if line:
                    plural = line
                else:
                    i = 0  # Start with next entry
                    print(formatEntry(header, description, None))
            elif i == 4:
                print(formatEntry(header, description, plural))
                plural = None
                i = 0  # Start with next entry
