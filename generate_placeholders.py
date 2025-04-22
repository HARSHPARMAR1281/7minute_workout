from PIL import Image, ImageDraw, ImageFont
import os

# Create output directory if it doesn't exist
os.makedirs('app/src/main/res/drawable-xxhdpi', exist_ok=True)

# Exercise names
exercises = [
    'step_up',
    'squats',
    'triceps_dip',
    'plank',
    'high_knees',
    'lunges',
    'pushup_rotation',
    'side_plank'
]

def create_placeholder(name):
    # Create a new image with a white background
    img = Image.new('RGBA', (512, 512), (255, 255, 255, 0))
    draw = ImageDraw.Draw(img)
    
    # Add text
    try:
        font = ImageFont.truetype("arial.ttf", 40)
    except:
        font = ImageFont.load_default()
    
    # Draw text in the center
    text = name.replace('_', ' ').title()
    text_bbox = draw.textbbox((0, 0), text, font=font)
    text_width = text_bbox[2] - text_bbox[0]
    text_height = text_bbox[3] - text_bbox[1]
    x = (512 - text_width) // 2
    y = (512 - text_height) // 2
    
    draw.text((x, y), text, fill=(0, 0, 0, 255), font=font)
    
    # Save as PNG
    img.save(f'app/src/main/res/drawable-xxhdpi/{name}.png', 'PNG')
    print(f"Created placeholder for {name}")

def main():
    for exercise in exercises:
        create_placeholder(exercise)

if __name__ == "__main__":
    main() 