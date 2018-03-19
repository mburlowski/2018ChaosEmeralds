import cv2
import numpy as np

data=[0,0,0]
##cap=cv2.VideoCapture(0) use if attempting vision with cam
while 1==1:
    ##ret,image=cap.read() use if attempting vision with cam
        
    image = cv2.imread("Desktop/visionCaptures/CaptureTwist2.PNG")
    blur=cv2.blur(image,(5,5))
    hsv=cv2.cvtColor(blur,cv2.COLOR_BGR2HSV)
    lower_green = np.array([180,245,0])
    upper_green = np.array([255,255,230])
    mask= cv2.inRange(blur,lower_green,upper_green)
    im2,contours,hierarchy= cv2.findContours(mask,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)
    if len(contours)>0:
        biggestContour = max(contours, key = cv2.contourArea) 
        m= cv2.moments(biggestContour)
        data[0]=cv2.contourArea(biggestContour)
        if m['m00']!=0:
            data[1]=int(m['m10']/m['m00'])
            data[2]=int(m['m01']/m['m00'])
    print(data)
    cv2.circle(image,(data[1],data[2]),5, (0,0,255), -1)
    cv2.imshow('a', image)

    key=cv2.waitKey(5)
    if(key==27):
        break
cv2.destroyAllWindows()
cap.release()
    


