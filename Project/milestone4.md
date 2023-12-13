<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone4</td></tr>
<tr><td> <em>Student: </em> Issa Aburomi (iaa47)</td></tr>
<tr><td> <em>Generated: </em> 12/13/2023 6:29:26 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-chatroom-milestone4/grade/iaa47" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Client can export chat history of their current session (client-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot of related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T18.41.48Screenshot%202023-12-13%20134138.png.webp?alt=media&token=d5958604-f6c0-49e8-b30c-4afea4e177f2"/></td></tr>
<tr><td> <em>Caption:</em> <p>Button showing the Export Chat<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot of exported data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T18.50.17Screenshot%202023-12-13%20134957.png.webp?alt=media&token=a8e5df41-c557-4f6e-8cca-425f029e54e8"/></td></tr>
<tr><td> <em>Caption:</em> <p>Code showing how the export chat function works<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T18.54.26Screenshot%202023-12-13%20135420.png.webp?alt=media&token=a0d3af39-d218-40e8-8e87-d5ba456338ed"/></td></tr>
<tr><td> <em>Caption:</em> <p>Terminal showing that the chat has been exported <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T18.57.33Screenshot%202023-12-13%20135704.png.webp?alt=media&token=f9241fbf-4b16-48df-9a7c-a30a70ad165f"/></td></tr>
<tr><td> <em>Caption:</em> <p>Showing the file that the chat was exported to<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>I created the export chat button that opens a filechooser where the user<br>can decide where to send the chat history and then it updates the<br>new/selected file and updates it with the chat history.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Client's mute list will persist across sessions (server-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot of how the mute list is stored</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.39.44Screenshot%202023-12-13%20164830.png.webp?alt=media&token=04c1f7a9-12cb-4182-ac0f-424377f3ff70"/></td></tr>
<tr><td> <em>Caption:</em> <p>I used an array list to store the list of the users who<br>have been muted<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the code saving/loading mute list</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.38.25Screenshot%202023-12-13%20164830.png.webp?alt=media&token=8e940f98-373a-43af-8f96-2104870a36ad"/></td></tr>
<tr><td> <em>Caption:</em> <p>Mute list code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>I created a muted client list that held the names of the muted<br>clients so that when they leave and join back they will still be<br>muted.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Client's will receive a message when they get muted/unmuted by another user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot showing the related chat messages</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T21.44.08Screenshot%202023-12-13%20164401.png.webp?alt=media&token=ec08459a-2a48-4938-a861-a414581c8a34"/></td></tr>
<tr><td> <em>Caption:</em> <p>Mute/unmute messages<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the related code snippets</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.10.54Screenshot%202023-12-13%20164830.png.webp?alt=media&token=709c0971-29d9-4e5d-b113-a7ce5d2df869"/></td></tr>
<tr><td> <em>Caption:</em> <p>Mute Client code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.10.17Screenshot%202023-12-13%20164638.png.webp?alt=media&token=a798ceb4-7799-4ae9-ab3f-348126e3a07e"/></td></tr>
<tr><td> <em>Caption:</em> <p>Send message code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.10.11Screenshot%202023-12-13%20164518.png.webp?alt=media&token=bda0e331-61fd-49eb-90d1-6357ae145249"/></td></tr>
<tr><td> <em>Caption:</em> <p>Mute/Unmute case<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.10.49Screenshot%202023-12-13%20164749.png.webp?alt=media&token=405338c1-3f30-4799-965b-03fde193be6f"/></td></tr>
<tr><td> <em>Caption:</em> <p>Mute repository method<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.11.06Screenshot%202023-12-13%20164803.png.webp?alt=media&token=db4af18e-c356-49e3-a6c5-a42271063e9e"/></td></tr>
<tr><td> <em>Caption:</em> <p>Handle Mute Unmute Request<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.11.22Screenshot%202023-12-13%20170913.png.webp?alt=media&token=f44f3eec-0b15-4919-8605-f36cb1ae72b8"/></td></tr>
<tr><td> <em>Caption:</em> <p>Mute and Unmute case<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T22.11.16Screenshot%202023-12-13%20164851.png.webp?alt=media&token=99f51877-8b57-4d39-affe-71efa5ee0dd3"/></td></tr>
<tr><td> <em>Caption:</em> <p>Unmute Client code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>Using the code I edited the server side and client side to make<br>the changes I needed to so that I could implement the mute and<br>unmute function.&nbsp; I changed the mute method so that it mutes someone and<br>sends them the message of who they were muted by.&nbsp; And the same<br>for the unmuted function.&nbsp; It allows the user to unmute someone and give<br>them a message saying they have been unmuted by the user.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> User list should update per the status of each user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot for Muted users by the client should appear grayed out</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T23.21.28Screenshot%202023-12-13%20182121.png.webp?alt=media&token=d04e7d57-6fcd-49b5-9aae-9c88512ec83b"/></td></tr>
<tr><td> <em>Caption:</em> <p>Code to highlight the code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot for Last person to send a message gets highlighted</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-12-13T23.29.13Screenshot%202023-12-13%20164401.png.webp?alt=media&token=d5b05b74-df3a-4106-8228-28308bde0726"/></td></tr>
<tr><td> <em>Caption:</em> <p>Highlighted code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>I had a lot of problems doing this part of the assignment.&nbsp; I<br>tried my best to go to office hours and show up to all<br>the classes but some of the code that I had done just would<br>not compile.&nbsp;&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-chatroom-milestone4/grade/iaa47" target="_blank">Grading</a></td></tr></table>
