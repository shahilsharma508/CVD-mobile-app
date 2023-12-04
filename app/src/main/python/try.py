def main(return_age,return_gender, return_blood_pressure, return_cholesterol, return_diabetes,return_smoking_status):
    import socket
    import json
    HEADER = 1054 #first client msg bytes
    PORT = 443
    FORMAT = 'utf-8'
    DISCONNECT_MESSAGE = "!DISCONNECT"
    SERVER = "66.228.62.177"
    ADDR = (SERVER,PORT)
    client= socket.socket(socket.AF_INET, socket.SOCK_STREAM) #setup socket
    client.connect(ADDR)

    parameter_list = [return_age,return_gender, return_blood_pressure,
                      return_cholesterol, return_diabetes,
                      return_smoking_status]
    send_list = json.dumps(parameter_list)
    message = send_list.encode(FORMAT) #encode string into bytes
    msg_length = len(message)
    send_length = str (msg_length).encode(FORMAT)
    send_length += b' ' * (HEADER - len(send_length))
    client.send(send_length)
    client.send(message)
    return_msg = client.recv(2048).decode(FORMAT)# receive msg from server

    message2 = DISCONNECT_MESSAGE.encode(FORMAT)
    msg_length2 = len(message2)
    send_length2 = str (msg_length2).encode(FORMAT)
    send_length2 += b' ' * (HEADER - len(send_length2))
    client.send(send_length2)
    client.send(message2)
    return return_msg 

    

