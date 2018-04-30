#!/usr/bin/python

import random
import pdb

def generator_imei():
	start_digit = "35"
	rest_digit = str(random.randint(0000000000000,9999999999999))
	
	return start_digit + rest_digit

def luhn_algorithm_checking(imei_number):
	result = 0
	
	if len(imei_number) != 15:
		return False
	else:
		check_digit = imei_number[14]
		for x in range(0,14):
			if x % 2 != 0:
				temp = int(imei_number[x]) * 2
				if temp > 9:
					temp2 = str(temp)
					temp3 = int(temp2[0]) + int(temp2[1])
					result += temp3

				else:
					result += temp
			else:
				result += int(imei_number[x])
				print("count %d: %d | %d" % (x,result,int(imei_number[x])))
		result += int(check_digit)
		if result % 10 == 0:
			print(imei_number+":"+str(result))

def main():
	
	
	for x in range(0,100):
		imei = generator_imei()
		luhn_algorithm_checking(imei)	
		
main()	
