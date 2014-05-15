import time, subprocess, os


gitpath = r"C:\Program Files (x86)\Git\bin\git.exe"
plinkpath = r"C:\Users\Alex\plink.exe"

def runcmd(args):
        si = subprocess.STARTUPINFO()
        si.dwFlags |= subprocess.STARTF_USESHOWWINDOW
        si.wShowWindow = subprocess.SW_HIDE
        proc = subprocess.Popen(args, executable=gitpath, stdin=None, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=False, startupinfo=si)
        stdout, stderr = proc.communicate()
        stdout = stdout.decode('utf-8', 'replace')
        stderr = stderr.decode('utf-8', 'replace')
        print(stdout)
        print(stderr)
        


def commit():
    runcmd("git add .")
    runcmd("git add -u")
    runcmd("git commit -m \"Automatic commit.\" --allow-empty")
    os.environ["GIT_SSH"] = plinkpath
    runcmd("git push origin master --force")

while True:
    time.sleep(15*60)
    commit()
