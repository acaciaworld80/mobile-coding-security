#!/usr/bin/python3

arrayofchar1 = [77, 61, 43, 198, 250, 192, 226, 254, 236, 232, 224, 233, 214, 227, 255, 171, 254, 209, 225, 255, 229, 205, 231, 252, 240, 201, 229, 253, 248, 246, 196, 250, 241, 255, 248, 255, 201, 195, 203, 197, 205, 199, 207, 201, 193, 203, 195, 205, 197, 207, 210]
count = 125 # 125 = } in ascii
result = []

for x in arrayofchar1:
	for y in range(33,127):
		m = 0xFF & (count ^ y)
		if m == x:
			result.append(y)
			count += 1
			break

print(result)
str1 = ''.join(chr(e) for e in result)

print(str1)

