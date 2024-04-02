from rembg import remove
from PIL import Image
import os

def remove_background(input_folder, output_folder):
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    files = os.listdir(input_folder)

    for file in files:
        input_path = os.path.join(input_folder, file)
        output_path = os.path.join(output_folder, file)
        print(input_path)
        with open(input_path, 'rb') as f:
            img = f.read()

        output_img = remove(img)

        with open(output_path, 'wb') as f:
            f.write(output_img)

        print(f"Background removed for {file}.")

input_folder = "sample/sample_image/before"
output_folder = "sample/sample_image/after"

remove_background(input_folder, output_folder)