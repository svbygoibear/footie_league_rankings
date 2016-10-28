#footie_league_rankings

#Synopsis
Create a command-line application that will calculate the ranking table for a soccer league.
<br>
- [ ] Reads in results from a text file.
<br>
- [ ] Generates results per team based on league data.
<br>
- [ ] Ranks results based on winners
<br>
- [ ] Handle tied outcomes

##Code Example
###Input/Output
The input and output will be text. Either using stdin/stdout or taking filenames on the command line is fine.

The input contains results of games, one per line. See “Sample input” for details.
The output should be ordered from most to least points, following the format specified in “Expected output”.

You can expect that the input will be well-formed. There is no need to add special handling for malformed input files.

###Deductions
From the instructions given, the following assumptions can be deducted from it:
- The input will be well-formed. There is no need to add special handling for malformed input files.
- The input contains results of games, one per line.
- In this league, a draw (tie) is worth 1 point and a win is worth 3 points. A loss is worth 0 points.
- If two or more teams have the same number of points, they should have the same rank and be printed in goal (home vs away) order (as in the tie for 3rd place in the sample data).

###Sample Input
This illustrates a sample input indicating a team name, followed by their result - the two participating teams per match are grouped together per line and is comma seperated.

```
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
```

###Code Snippet
Todo:

```javascript
Todo
```

###Expected Output
As seen here, the league results (or output) is formatted from the team with the most points to the team with the least points, one teamm per line with their results indicated after the first comma in that line.

```
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
5. Grouches, 0 pts
```

##Installation
ToDo

##Contributors
Feel free to pop me a message or flag an issue if you come across it - I'll see what I can do about it.

##License

Copyright © `2016` `Simone van Buuren`

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the “Software”), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.