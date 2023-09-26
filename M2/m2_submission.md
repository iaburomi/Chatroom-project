<table><tr><td> <em>Assignment: </em> IT114 M2 Java-HW</td></tr>
<tr><td> <em>Student: </em> Issa Aburomi (iaa47)</td></tr>
<tr><td> <em>Generated: </em> 9/25/2023 9:02:00 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-m2-java-hw/grade/iaa47" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p><br></p><p><strong>Template Files</strong>&nbsp;You can find all 3 template files in this gist:&nbsp;<a href="https://gist.github.com/MattToegel/fdd2b37fa79a06ace9dd259ac82728b6">https://gist.github.com/MattToegel/fdd2b37fa79a06ace9dd259ac82728b6</a>&nbsp;<br></p><p>Setup steps:</p><ol><li><code>git checkout main</code></li><li><code>git pull origin main</code></li><li><code>git checkout -b M2-Java-HW</code></li></ol><p>You'll have 3 problems to save for this assignment.</p><p>Each problem you're given a template&nbsp;<strong>Do not edit anything in the template except where the comments tell you to</strong>.</p><p>The templates are done in such a way to make it easier to capture the output in a screenshot.</p><p>You'll copy each template into their own separate .java files, immediately git add, git commit these files (you can do it together) so we can capture the difference/changes between the templates and your additions. This part is required for full credit.</p><p>HW steps:</p><ol><li>Open VS Code at the root of your repository folder</li><li>In VS Code create a new folder/directory called M2</li><li>Create 3 new files in this new M2 folder (Problem1.java, Problem2.java, Problem3.java)</li><li>Paste each template into their respective files</li><li><code>git add .</code></li><li><code>git commit -m "adding template baselines</code></li><li>Do the related work (you may do steps 8 and 9 as often as needed or you can do it all at once at the end)</li><li><code>git add .</code></li><li><code>git commit -m "completed hw"</code></li><li>When you're done push the branch<ol><li><code>git push origin M2-Java-HW</code></li></ol></li><li>Create the Pull Request with <b>main</b>&nbsp;as base and&nbsp;<strong>M2-Java-HW</strong>&nbsp;as compare (don't merge/close it yet)</li><li>Create a new file in the M2 folder in VS Code called m2_submission.md</li><li>Fill out the below deliverable items, save the submission, and copy to markdown</li><li>Paste the markdown into the m2_submission.md</li><li>add/commit/push the md file<ol><li><code>git add m2_submission.md</code></li><li><code>git commit -m "adding submission file"</code></li><li><code>git push origin M2-Java-HW</code></li></ol></li><li>Merge the pull request from step 11</li><li>On your local machine sync the changes<ol><li><code>git checkout main</code></li><li><code>git pull origin main</code></li></ol></li><li>Submit the link to the m2_submission.md file from the main branch to Canvas</li></ol><p><br></p></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Problem 1 - Only output Odd values of the Array under "Odds output" </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> 2 Screenshots: Clearly screenshot the output of Problem 1 showing the data and show the code</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-09-26T00.20.59Screenshot%202023-09-25%20202046.png.webp?alt=media&token=ef5a8b24-a98f-4e41-a148-77d51ecfa124"/></td></tr>
<tr><td> <em>Caption:</em> <p>Problem 1 Code:<br>Everything is done in processArray, processArray is only handling arr and<br>not a1, a2, a3, a4 directly, Only odd values are being output, and<br>processArray()&#39;s initial two outputs are unedited and still at the beginning of the<br>method<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-09-26T00.16.42Screenshot%202023-09-25%20201446.png.webp?alt=media&token=e0c44ef6-787b-4657-a79c-b816bd654c76"/></td></tr>
<tr><td> <em>Caption:</em> <p>Problem 1 Output<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Describe how you solved the problem</td></tr>
<tr><td> <em>Response:</em> <p>I am taking the numbers in the arrays and creating an if statement<br>that removes the odd numbers from the arrays.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Problem 2 - Only output the sum/total of the array values (the number must end in 2 decimal places, if it ends in 1 it must have a 0 at the end) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> 2 Screenshots: Clearly screenshot the output of Problem 2 showing the data and show the code</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-09-26T00.32.43Screenshot%202023-09-25%20203234.png.webp?alt=media&token=45d1db6a-95ec-4e3f-a446-883935ecef79"/></td></tr>
<tr><td> <em>Caption:</em> <p>Problem 2 code: <br>Edits were done only in getTotal(), getTotal() is only handling<br>arr and not a1, a2, a3, a4 directly, Each total is properly rounded<br>to two decimals always, not more, not less (displayed like currency) i.e., 1.00,<br>1.10, 1.01, etc, getTotal()&#39;s initial output is unedited and still at the beginning<br>of the method, getTotal()&#39;s last two outputs are unedited and remain at the<br>end of the method<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-09-26T00.33.24Screenshot%202023-09-25%20201549.png.webp?alt=media&token=684744c2-c8c2-4b16-a4d9-2e7eeeba8e78"/></td></tr>
<tr><td> <em>Caption:</em> <p>Problem 2 Output<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Describe how you solved the problem</td></tr>
<tr><td> <em>Response:</em> <p>I created an if statement that takes each number and prints it to<br>the total so that all the numbers in the array add up all<br>together and give us the output.&nbsp; I took totalOutput and changed the format<br>of the Output so that it sets the output to a hundredth decimal<br>place using %.2f.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Problem 3 - Output the given values as positive under the "Positive Output" message (the data otherwise shouldn't change) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> 2 Screenshots: Clearly screenshot the output of Problem 3 showing the data and show the code</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-09-26T00.57.22Screenshot%202023-09-25%20205643.png.webp?alt=media&token=0d954f1b-c347-4f81-82dd-31ee9144d41a"/></td></tr>
<tr><td> <em>Caption:</em> <p>Problem 3 Code<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-09-26T00.36.16Screenshot%202023-09-25%20203603.png.webp?alt=media&token=48cc0bd3-55e8-43f8-a3de-0414c550d75e"/></td></tr>
<tr><td> <em>Caption:</em> <p>Problem 3 Output<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fiaa47%2F2023-09-26T00.57.29Screenshot%202023-09-25%20205706.png.webp?alt=media&token=9a1ffcbc-331a-4ac9-8432-0a2f0d8364a4"/></td></tr>
<tr><td> <em>Caption:</em> <p>Problem 3 Code<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Describe how you solved the problem</td></tr>
<tr><td> <em>Response:</em> <p>To create the positive conversion I created an if statement that takes the<br>integer in the array and if it is negative I run it through<br>the math.abs function that takes the number and returns the absolute zero of<br>that integer.&nbsp; Then I did the same for the two arrays containing doubles.&nbsp;<br>For the string value, I had to rewrite the string values with an<br>if statement that if the string has a &quot;-&quot; it is to be<br>replaced with a blank value (&quot; &quot;) to make it positive.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Misc Items </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707834-bf5a5b13-ec36-4597-9741-aa830c195be2.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Pull Request URL for M2-Java-HW to main</td></tr>
<tr><td>Not provided</td></tr>
<tr><td> <em>Sub-Task 2: </em> Talk about what you learned, any issues you had, how you resolve them</td></tr>
<tr><td> <em>Response:</em> <p>I learned how to change numbers from negative to positive using integers, doubles,<br>and strings.&nbsp; I had some trouble with problem 3 but after I went<br>to office hours I was able to understand it better.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-003-F23/it114-m2-java-hw/grade/iaa47" target="_blank">Grading</a></td></tr></table>
