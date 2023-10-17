<table><tr><td> <em>Assignment: </em> IT114 - Sockets Part 1 - 3</td></tr>
<tr><td> <em>Student: </em> Issa Aburomi (iaa47)</td></tr>
<tr><td> <em>Generated: </em> 10/16/2023 8:21:10 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-sockets-part-1-3/grade/iaa47" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create a new branch for this assignment</li><li>Go through the socket lessons and get each part implemented (parts 1-3)</li><ol><li>You'll probably want to put them into their own separate folders/packages (i.e., Part1, Part2, Part3) These are for your reference</li><li>Part 3, below, is what's necessary for this HW</li><li><a href="https://github.com/MattToegel/IT114/tree/Module4/Module4/Part3">https://github.com/MattToegel/IT114/tree/Module4/Module4/Part3</a><br></li></ol><li>Create a new folder called Part3HW</li><li>Create an empty m4_submission.md file in Part3HW (or skip this step and download the file at the end)</li><ol><li>Add/commit/push the branch</li><li>Create a pull request to main and keep it open</li></ol><li>Copy the the Part3 code into this new folder (Part3HW)</li><li>Git add/commit all of the sample code and the Part3HW code</li><li>Implement <b>two </b>of the following <b>server-side</b> activities for all connected clients (majority of the logic should be processed server-side and broadcasted/sent to all clients if/when applicable)</li><ol><li>Simple number guesser where all clients can attempt to guess while the game is active</li><ol><li>Have a start command that activates the game allowing guesses to be interpreted</li><li>Have a stop command that deactivates the game, guesses will be treated as regular messages</li><li>Have a guess command that include a value that is processed to see if it matches the hidden number (i.e., <i>guess 5</i>)</li><ol><li>Guess should only be considered when the game is active</li><li>The response should include who guessed, what they guessed, and whether or not it was correct (i.e., Bob guessed 5 but it was not correct)</li></ol><li>No need to implement complexities like strikes</li></ol><li>Coin toss command (random heads or tails)</li><ol><li>Command should be something logical like flip or toss or coin or similar</li><li>The result should mention <i>who</i>&nbsp;did <i>what</i>&nbsp;and got what <i>result</i>&nbsp;(i.e., Bob Flipped a coin and got heads)</li></ol><li>Dice roller given a command and text format of "roll #d#" (i.e., roll 2d6)</li><ol><li>Command should be in the format of roll #d# (i.e., roll 1d10)</li><li>The result should mention&nbsp;<i>who</i>&nbsp;did&nbsp;<i>what</i>&nbsp;and got what&nbsp;<i>result</i>&nbsp;(i.e., Bob rolled 1d10 and got 7)</li></ol><li>Math game (server outputs a basic equation, first person to guess it correctly gets congratulated and a new equation is given)</li><ol><li>Have a start command that activates the game allowing equaiton to be answered</li><li>Have a stop command that deactivates the game, answers will be treated as regular messages</li><li>Have an answer command that include a value that is processed to see if it matches the hidden number (i.e.,&nbsp;<i>answer 15</i>)<br></li><ol><li>The response should include who answered, what they answered, and whether or not it was correct (i.e., Bob answered 5 but it was not correct)</li></ol></ol><li>Private message (a client can send a message targetting another client where only the two can see the messages)</li><ol><li>Command can be pm, dm or an @ preceding the users name (clearly note which)</li><li>The server should properly check the target audience and send the response to the original sender and to the receiver</li><li>Alternatively (make note if you do this and show evidence) you can add support to private message multiple people at once. Evidence should show a larger number of clients than the target list of the private message to show it works. Note to grader: if this is accomplished add 0.5 to total final grade on Canvas.</li></ol><li>Message shuffler (randomizes the order of the characters of the given message)</li><ol><li>Command should be shuffle or randomize (clearly mention what you chose)</li><li>The message should be sent to all clients showing it's from the user but randomized</li><ol><li>Example: Bob types <i>command</i>&nbsp;hello and everyone recevies Bob: lleho</li></ol></ol></ol><li>Fill in the below deliverables</li><li>Save and generated the markdown or markdown file</li><li>Update the m4_submission.md file in the Part3HW folder</li><li>Add/commit/push your changes</li><li>Merge the pull request</li><li>From the M4-Sockets branch, navigate to your m4_submission.md file on github and copy the link</li><li>Submit the direct link to Canvas</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Baseline </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add as many screenshots as necessary to show basic communication among 3 clients and 1 server</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-17T00.19.08Screenshot%202023-10-16%20201455.png.webp?alt=media&token=f8afd2ea-4029-4d99-9ff7-7bbdf35fd678"/></td></tr>
<tr><td> <em>Caption:</em> <p>Part 1<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-17T00.19.55Screenshot%202023-10-16%20201940.png.webp?alt=media&token=09fc7f77-153c-4b8f-aabd-61e848420537"/></td></tr>
<tr><td> <em>Caption:</em> <p>Part 2<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-17T00.19.59Screenshot%202023-10-16%20201948.png.webp?alt=media&token=6eb999fb-9163-443f-a0d0-1decafce8c6a"/></td></tr>
<tr><td> <em>Caption:</em> <p>Part 3<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Feature Implementation 1 </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> What feature did you pick? Briefly explain how you implemented it</td></tr>
<tr><td> <em>Response:</em> <p>I picked the dice roll.&nbsp; I used the rollDice method that outputs a<br>random number on the die.&nbsp; Then an if statement&nbsp;<br></p><br></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot(s) showing the implemented feature working</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Add screenshot(s) of related code changes to highlight the new logic</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-17T00.06.34Screenshot%202023-10-16%20200603.png.webp?alt=media&token=db67412a-480a-464a-8b75-06cca6f54884"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code is an if statement saying that if the user types Roll<br>in the terminal it will trigger the dice method to produce a random<br>number and then print a return statement that tells the user what number<br>he got<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-17T00.06.38Screenshot%202023-10-16%20200612.png.webp?alt=media&token=03b22db1-ca8c-495c-bebd-ebfebdbce2b5"/></td></tr>
<tr><td> <em>Caption:</em> <p>This code is the rollDice method that produces a random number on the<br>die.<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Feature Implementation 2 </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> What feature did you pick? Briefly explain how you implemented it</td></tr>
<tr><td> <em>Response:</em> <p>&nbsp;I chose the coin flip challenge.&nbsp; For flip coin, I used a random<br>Boolean to print out heads or tails&nbsp;<br></p><br></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot(s) showing the implemented feature working</td></tr>
<tr><td><table><tr><td>Missing Image</td></tr>
<tr><td> <em>Caption:</em> (missing)</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Add screenshot(s) of related code changes to highlight the new logic</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-17T00.00.30Screenshot%202023-10-16%20200008.png.webp?alt=media&token=814a2564-6343-401a-a6a6-dda0406963aa"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is a Flip Coin Code written by an if statement that returns<br>the output of the randomly generated side of the coin.  It allows<br>the user to use the commands flip, toss, or coin function so that<br>it triggers the coin toss.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-17T00.00.33Screenshot%202023-10-16%20195939.png.webp?alt=media&token=93f33e3c-804f-4c69-a361-f7a8bbf2f706"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is the second part of the flip coin code which picks a<br>random side of the coin.<br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Did you have an issues and how did you resolve them? If no issues, what did you learn during this assignment that you found interesting?</td></tr>
<tr><td> <em>Response:</em> <p>I had a couple of trouble figuring out how to show the output<br>of the program but after watching the video I figured out how to<br>output the program.<br></p><br></td></tr>
<tr><td> <em>Sub-Task 2: </em> Pull request link</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/iaburomi/Iaa47-IT114-003/pull/4">https://github.com/iaburomi/Iaa47-IT114-003/pull/4</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-sockets-part-1-3/grade/iaa47" target="_blank">Grading</a></td></tr></table>