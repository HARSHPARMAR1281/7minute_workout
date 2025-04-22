import os
import requests
from PIL import Image
from io import BytesIO

# Exercise images mapping
exercises = {
    'step_up': 'https://example.com/step_up.jpg',  # Replace with actual image URLs
    'squats': 'https://example.com/squats.jpg',
    'triceps_dip': 'https://example.com/triceps_dip.jpg',
    'plank': 'https://example.com/plank.jpg',
    'high_knees': 'https://example.com/high_knees.jpg',
    'lunges': 'https://example.com/lunges.jpg',
    'pushup_rotation': 'https://example.com/pushup_rotation.jpg',
    'side_plank': 'https://example.com/side_plank.jpg'
}

def download_and_process_image(url, filename):
    try:
        # Download image
        response = requests.get(url)
        img = Image.open(BytesIO(response.content))
        
        # Resize to 512x512
        img = img.resize((512, 512), Image.LANCZOS)
        
        # Save as PNG
        img.save(f'app/src/main/res/drawable-xxhdpi/{filename}.png', 'PNG')
        print(f"Successfully processed {filename}")
    except Exception as e:
        print(f"Error processing {filename}: {str(e)}")

def main():
    for exercise, url in exercises.items():
        download_and_process_image(url, exercise)

if __name__ == "__main__":
    main() 