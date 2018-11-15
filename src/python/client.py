import socket
from process import process_image 

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
addr = ("127.0.0.1", 7777)

client_socket.connect(addr)

def recvall(sock):
    BUFFER = 4096
    data = b''
    
    try:
        while True:
            part = sock.recv(BUFFER)
            data += part
            if len(part) < BUFFER:
                break
    
    except socket.error as msg:
        print ("Socket Error: %s" % msg)
    
    finally:
        sock.close()
    
    return data

process_image(recvall(client_socket).decode("utf-8")) # Use decode to convert bytes to str