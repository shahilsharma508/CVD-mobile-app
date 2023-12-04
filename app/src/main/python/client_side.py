import socket
import json

def main(return_age,return_gender, return_blood_pressure, return_cholesterol, return_diabetes,return_smoking_status):
 

    HEADER = 1054 #first client msg bytes
    PORT = 5050
    FORMAT = 'utf-8'
    DISCONNECT_MESSAGE = "!DISCONNECT"
    SERVER = "10.158.17.46"
    ADDR = (SERVER,PORT)
    client= socket.socket(socket.AF_INET, socket.SOCK_STREAM) #setup socket
    client.connect(ADDR)

    parameter_list = [return_age,return_gender, return_blood_pressure,return_cholesterol, return_diabetes,return_smoking_status]
    
    send_list = json.dumps(parameter_list)
    
    message = send_list.encode(FORMAT) #encode string into bytes
    msg_length = len(message)
    send_length = str (msg_length).encode(FORMAT)
    send_length += b' ' * (HEADER - len(send_length))
    client.send(send_length)
    client.send(message)
    return_msg = client.recv(2048).decode(FORMAT)# receive msg from server
    return return_msg


msg = main("23","1", "160", "5","1","0")
print(msg)

