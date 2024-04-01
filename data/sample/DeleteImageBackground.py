from rembg import remove
from PIL import Image
import os

categorys = ['top', 'bottom', 'outer']

for category in categorys:
    for i in range(1, 11):
        file_name = f'{category}{i}.png'
        script_dir = os.path.dirname(__file__)
        input_path = os.path.join(script_dir, f'sample_image/before/{file_name}')
        input = Image.open(input_path)
        output = remove(input)
        output_path = os.path.join(script_dir, f'sample_image/after/{file_name}')
        output.save(output_path)