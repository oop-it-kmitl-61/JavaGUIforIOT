![logo](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/logo.png)

# JavaGUIforIOT
โครงงานการพัฒนา Java Desktop Application สำหรับการควบคุมอุปกรณ์ IOT
"Home Security"

#  ![Help](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/Help.png) Report
> https://drive.google.com/file/d/1r_P4g6XcoCOLkvR7XCuOzkfM1Y2MdSd7/view?usp=sharing

#  ![home](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/Home.png)  About us
ในปัจจุบันเทคโนโลยีได้มีการพัฒนาอย่างก้าวกระโดด ทำให้เกิดนวัตกรรมใหม่ๆ เกิดขึ้น
บนโลก รวมถึงการที่มีระบบเครือข่ายอินเทอร์เน็ตที่คนเราใช้กันอีกด้วย และภายหลังมีการนำ
อินเทอร์เน็ตมาใช้ในการทำสิ่งต่าง ๆ เพิ่มมากขึ้น จนเกิดคำนิยามของคำว่า IoT หรือ Internet of
Things ทางผู้จัดทำจึงเกิดแนวคิดในการควบคุม อุปกรณ์ IOT ได้ง่ายขึ้น ใช้ Java Application ในการ
ควบคุมฐานข้อมูลแบบ Real-Time (Firebase) โดยภาษาหลักที่ใช้ประกอบด้วย ภาษา Java เป็นหลัก ภาษา
C++ และ Javascript(Node js) รองลงมาตามลำดับ

#  ![Hardwares](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/hard.png) Hardwares
- Arduino Uno
- NodeMCU
- DS3231 Temperature Sensor 
- MQ2 Gas Sensor

#  ![app preview](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/preview.png) Application Preview

![app](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/gui.png)

![graph](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/graph.png)


#  ![How to use](https://github.com/compro-itkmitl/777-traker/blob/master/resouces/Settings.png) How to use
## วิธีการเปิดโปรแกรม

ใช้ JDK version 1.8 ดาวน์โหลดโฟลเดอร์นี้

> https://goo.gl/UavCDD

จากนั้นสามารถเปิดโปรแกรมได้โดยใช้ Terminal หรือ Commandline ด้วยคำสั่งนี้

`java -jar "JavaGUIforIOT.jar" `

## การเตรียม ฐานข้อมูลหากต้องการใช้ฐานข้อมูลของตัวเอง

1. สามารถเข้าไปสมัครและสร้างฐานข้อมูลได้ที่ [Firebase](https://firebase.google.com/)
2. เข้าไป download file service account ตามภาพ กด generate new private key
![fire1](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/firebase1.png)
![fire2](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/firebase2.png)
![fire3](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/firebase3.png)
3. ไปที่ real-time database พร้อมกับก็อบปี้ URL
![url](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/firebase4.png)
4. ทำการ config path ต่าง ๆ ได้ในไฟล์ config.json
5. โครงสร้างฐานข้อมูลดังภาพต่อไปนี้ โดยทั้งหมดต้องตั้งค่าเป็น String
![struct](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/firebase5.png)
6. (Optional) สามารถใช้ Firebase Function เพื่อให้มีการเก็บ log อัตโนมัติ ได้โดย ใช้ไฟล์ index.js ในโฟลเดอร์  firebaseFunction และใช้ library moment.js ซึ่งสามารถหาโหลดได้
![function](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/functions.png)

## Library
หากโปรแกรมค้นหาไฟล์ Library ไม่พบ สามารถดาวน์โหลดได้จาก
> https://goo.gl/Qu2WG2

### ฟังก์ชั่นต่าง ๆ ของโปรแกรม
1. หลังจากที่เปิดโปรแกรมจะทำการแสดง GUI หน้าแรกของโปรแกรม ซึ่ง ค่าที่ได้จาก Sensor จะแสดงข้อมูลที่เป็นปัจจุบัน (ข้อมูลที่ได้จาก Firebase)
![fire1](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/firebase1.png)

2.เราสามารถตั้งค่ากำหนดอุณหภูมิหรือปริมาณควันที่จะควบคุม เพื่อทำการ แจ้งเตือนภัย ในกรณีที่มีค่าอุณหภูมิหรือปริมาณควันที่มากเกินไป ด้วยเสียงและ คำแจ้งเตือนที่เป็นสีแดง ซึ่งสามารถกำหนดได้โดยทำการใส่ค่าลงไปในช่อง “อุณหภูมิที่จะควบคุม” และ “ปริมาณควันที่จะควบคุม” ลงไปแล้วกด “บันทึก” ภาพที่ 9 GUI หน้าแรกของโปรแกรม เมื่อมีการตั้งค่าจุดวิกฤต และมีการแจ้งเตือน ด้วยคำแจ้งเตือนสีแดง และมีเสียงแจ้งเตือนที่ตั้งค่าไว้
![howto1](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/howtouse1.png)

3.นอกจากนี้เรายังสามารถตั้งค่าเสียงแจ้งเตือนได้โดยการคลิกที่ “ตั้งค่าเสียง” จากนั้นก็จะปรากฏหน้าต่าง File Dialog ขึ้นมา ให้เราทำการเลือกไฟล์เสียงใน ในปัจจุบันรองรับสกุลไฟล์ WAV และมีขนาดสั้นเท่านั้น
![howto2](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/howtouse2.png)

4.ตัวโปรแกรมมีระบบประมวลผลของกราฟแสดงค่าเฉลี่ยของ อุณหภูมิและปริมาณแก๊สในแต่ละวัน โดยเราสามารถคลิก “กราฟค่าเฉลี่ย อุณหภูมิ” หรือ “กราฟค่าเฉลี่ยควัน” ในหน้าหลัก ซึ่งจะแสดงผลดังนี้
![howto3](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/howtouse3.png)

5.ถ้าหากข้อมูลในกราฟมีปริมาณที่มากเกินไป จะมี Scrollbar ปรากฎขึ้นด้านล่าง
![howto5](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/howtouse5.png)

6.ตัวกราฟสามารถเซฟเป็น png ได้
![howto4](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/howtouse4.png)

#  ![Members](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/Person.png) Members
| Name          | Surname       | Student ID |
| :------------ |:------------- |   :-----:  |
| Panupong      | Ketrattanakul |   60070072 |
| Phuree        | Kanusont      |   60070075 |
| Yutthana      | Srithamboon   |   60070078 |

#  ![Supervisors](https://github.com/oop-it-kmitl-61/JavaGUIforIOT/blob/master/resouces/Supervisor.png) Supervisors
| Supervisors         |
| :------------ |
| Asst.prof. Thanisa Numnonda, Ph.D. |


> โครงงานนี้เป็นส่วนหนึ่งของวิชา OBJECT-ORIENTED PROGRAMMING (รหัส 06016317) คณะเทคโนโลยีสารสนเทศ สถาบันเทคโนโลยีพระจอมเกล้าเจ้าคุณทหารลาดกระบัง
