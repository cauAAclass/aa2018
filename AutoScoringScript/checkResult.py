Notice = '''
JDK 설정(Windows 기준, 11버전)

1. https://jdk.java.net/11/ 에 들어가서 Build 다운받고
2. 압축 풀고, 그 경로들(하위경로 포함) 전부 다 포함해서 시스템의 Path 에 전부 추가하고
3. cmd를 켜본 다음에 javac와 java를 쳐서 잘 되는지 확인하다.
4. 본인 코드를 예제 코드에 적혀있는 규칙으로 넣어보고, 잘 되는지 확인한다.

<주의!>
* 이메일로 파일을 제출하실 때, 파일첨부에 java만 첨부하세요. 이외의 파일확장자로 첨부되는 경우 채점대상에서 제외됩니다.
* 15분마다 한번씩 받은 메일의 첨부파일을 파싱하는 매크로를 쓰고 있습니다. 만약 코드 파일을 다시 보내야 한다면 15~20분 뒤에 다시 보내주세요.
* 위 이야기로 말미암았을때, 파일제목 헷갈리셔서 ospp2 코드를 ospp1으로 해서 보내시면, 난리가 나겠죠?
* 2번째 프로젝트라면 ospp2로 바꾸시고, 코드 안에 class 이름도 바꾸셔야 합니다. 클래스에 들어가는 숫자에 따라 채점파일이 바뀌게 됩니다.
* 패키지 이름 쓰지 마세요. 패키지에 넣으면 main을 부를 때 문제가 생기므로 채점시 문제가 생깁니다.

* 파일 이름은  ospp[프로젝트번호]_[학번].java 처럼 정확하게 적혀 있어야 합니다.
ex)  ospp1_20124902.java

-조교 올림-
'''

TestFile =   {1:'TestFile.csv', 2:'TestFile.csv', 3:'미정미!정.Error'}
TestAnswerFile = {1:'__result_ospp1.txt', 2:'__result_ospp2.txt', 3:'영미!'}

# Testing Script 정리
import glob
import os

Error_Complie = []
Error_Result = []
Pass_Student = []

for JavaFileName in glob.glob("*.java"):
    FileName = JavaFileName.split('.')[0]
    FileInfo = JavaFileName.replace('ospp','')
    FileInfo = FileInfo.replace('.','_')
    FileInfo = FileInfo.split('_')

    projectNum = int(FileInfo[0])
    StudentID = FileInfo[1]

    print('\n *', str(StudentID), "ㅡ"*20)

    #print(FileInfo)
    ComplieCmd = 'javac '+JavaFileName
    JavaClassFileName = FileName+'.class'
    TestResultFile = str(projectNum)+'_'+str(StudentID)+'.txt'

    JavaCallCmd = 'java '+FileName +' '+ TestFile[projectNum]+' '+TestResultFile
    
    #Complie Script
    print(">"*3,ComplieCmd)
    os.system(ComplieCmd)
    if len(glob.glob(JavaClassFileName))==0:
        print("\nComplie Error!\n")
        Error_Complie.append(StudentID)
        continue
    
    print(">"*3,'java '+JavaCallCmd)
    os.system(JavaCallCmd)
    if len(glob.glob(TestResultFile))==0:
        print("\nCode Error!\n")
        Error_Complie.append(StudentID)
        os.remove(FileName+'.class')
        continue
    
    with open(TestAnswerFile[projectNum], 'r') as File:
        RealAns = File.readlines()    
    with open(TestResultFile, 'r') as File:
        UserAns = File.readlines()
    
    if RealAns[0] != UserAns[0]:
        print("\nResult Error!\n")
        Error_Result.append(StudentID)
        os.remove(FileName+'.class')
        continue

    print("\nPass!\n")
    Pass_Student.append(StudentID)
    os.remove(FileName+'.class')

print("\nComplie Error or Code Error: ",Error_Complie)
print("\nResult Error: ",Error_Result)
print("\nPass! ",Pass_Student)
