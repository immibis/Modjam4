import time
import subprocess


gitpath = r'C:\Program Files (x86)\Git\bin\git.exe'

def runcmd(args):
        proc = subprocess.Popen(args, executable=gitpath, stdin=None, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=False)
        stdout, stderr = proc.communicate()
        stdout = stdout.decode('utf-8', 'replace')
        stderr = stderr.decode('utf-8', 'replace')
        print(stdout)
        print(stderr)
        


runcmd("git add .")
runcmd("git add -u")
runcmd("git commit -m \"Automatic commit.\"")
