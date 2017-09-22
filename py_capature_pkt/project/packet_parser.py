# encoding: utf-8
'''
该文件的函数负责从数据包中读取各个字段的值
所有函数请务必遵循命名格式
get_字段名称
例如 get_source_ip
'''
def get_source_ip(pkt):
    return pkt.ip.src

def get_destination_ip(pkt):
    return pkt.ip.dst

def get_snifftime(pkt):
    return pkt.sniff_timestamp

def get_rtt(pkt):
    return pkt.tcp.analysis_ack_rtt

def get_source_port(pkt):
    return pkt[pkt.transport_layer].srcport

def get_destinaition_port(pkt):
    return pkt[pkt.transport_layer].dstport

def get_protocol(pkt):
    return pkt.highest_layer