<table><tr><td> <em>Assignment: </em> It114 Milestone1</td></tr>
<tr><td> <em>Student: </em> Issa Aburomi (iaa47)</td></tr>
<tr><td> <em>Generated: </em> 10/23/2023 12:24:41 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-milestone1/grade/iaa47" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create a new branch called Milestone1</li><li>At the root of your repository create a folder called Project if one doesn't exist yet</li><ol><li>You will be updating this folder with new code as you do milestones</li><li>You won't be creating separate folders for milestones; milestones are just branches</li></ol><li>Create a milestone1.md file inside the Project folder</li><li>Git add/commit/push it to Github (yes it'll be blank for now)</li><li>Create a pull request from Milestone1 to main (don't complete/merge it yet, just have it in open status)</li><li>Copy in the latest Socket sample code from the most recent Socket Part example of the lessons</li><ol><li>Recommended Part 5 (clients should be having names at this point and not ids)</li><li><a href="https://github.com/MattToegel/IT114/tree/Module5/Module5">https://github.com/MattToegel/IT114/tree/Module5/Module5</a>&nbsp;<br></li></ol><li>Fix the package references at the top of each file (these are the only edits you should do at this point)</li><li>Git add/commit the baseline</li><li>Ensure the sample is working and fill in the below deliverables</li><ol><li>Note: The client commands likely are different in part 5 with the /name and /connect options instead of just connect</li></ol><li>Get the markdown content or the file and paste it into the milestone1.md file or replace the file with the downloaded version</li><li>Git add/commit/push all changes</li><li>Complete the pull request merge from step 5</li><li>Locally checkout main</li><li>git pull origin main</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Startup </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot showing your server being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-23T16.09.06Screenshot%202023-10-23%20120859.png.webp?alt=media&token=89a7e113-373d-4721-baac-70723dff9610"/></td></tr>
<tr><td> <em>Caption:</em> <p>Server Working<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot showing your client being started and running</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-23T16.09.14Screenshot%202023-10-23%20120859.png.webp?alt=media&token=f37c9049-f6dd-40eb-9dfc-243fdb5223c3"/></td></tr>
<tr><td> <em>Caption:</em> <p>Client connected to server<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the connection process</td></tr>
<tr><td> <em>Response:</em> <p>The server gives me a local host server to connect the clients and<br>I have to connect the clients through the number the server gives me.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Sending/Receiving </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-23T16.11.05Screenshot%202023-10-23%20120859.png.webp?alt=media&token=59c8018e-827a-4032-a659-d44ae500494e"/></td></tr>
<tr><td> <em>Caption:</em> <p>Clients and server connecting<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the messages are sent, broadcasted (sent to all connected clients), and received</td></tr>
<tr><td> <em>Response:</em> <p>The clients send messages to the serverThread then gets sent to a chatroom<br>between the two clients which then sends the message to the other client.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Disconnecting / Terminating </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot(s) showing evidence related to the checklist</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-10-23T16.14.08Screenshot%202023-10-23%20121401.png.webp?alt=media&token=38b4a1e2-72b4-432c-9703-2f90755c6931"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows client leaving chatroom<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how the various disconnects/terminations are handled</td></tr>
<tr><td> <em>Response:</em> <p>I use the phrase quit to trigger the client to disconnect from the<br>socket.&nbsp; It does not crash the server because it only disconnects one client<br>and since the other client is still connected the server is still up.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add the pull request for this branch</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/iaburomi/Iaa47-IT114-003/pull/7">https://github.com/iaburomi/Iaa47-IT114-003/pull/7</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Talk about any issues or learnings during this assignment</td></tr>
<tr><td> <em>Response:</em> <p>I did not have any issues with this assignment.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-milestone1/grade/iaa47" target="_blank">Grading</a></td></tr></table>