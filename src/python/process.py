import json
import numpy as np
import cv2

def process_image(data):
    data_list = json.loads(data) # Parse "json string" to a list
    image_data = np.array(data_list, dtype = np.uint8)

    cv2.imshow("Image", image_data)
    cv2.imwrite("img.png", image_data)
    cv2.waitKey(0)