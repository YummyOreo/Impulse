import os
from os import *
import urllib.request
import time

minecraftFolder = input("Past your .minecraft\\vesions folder: ")

print("Checking if the directory exists")
if path.exists(f"{minecraftFolder}\Impulse\\"):
	print("Clearing the files")
	remove(f"{minecraftFolder}\Impulse\Impulse.jar")
	remove(f"{minecraftFolder}\Impulse\Impulse.json")
	open(f"{minecraftFolder}\Impulse\Impulse.jar", os.O_CREAT)
	open(f"{minecraftFolder}\Impulse\Impulse.json", os.O_CREAT)
else:
	print("Making the files")
	mkdir(f"{minecraftFolder}\Impulse\\")
	open(f"{minecraftFolder}\Impulse\Impulse.jar", os.O_CREAT)
	open(f"{minecraftFolder}\Impulse\Impulse.json", os.O_CREAT)

print("Pasting contents into the files!")
urllib.request.urlretrieve('https://github.com/YummyOreo/impulse/raw/main/DoNotDownload/Impulse.jar', f'{minecraftFolder}\Impulse\Impulse.jar')
urllib.request.urlretrieve('https://github.com/YummyOreo/impulse/raw/main/DoNotDownload/Impulse.json', f'{minecraftFolder}\Impulse\Impulse.json')
print("Done!")
time.sleep( 5 )
